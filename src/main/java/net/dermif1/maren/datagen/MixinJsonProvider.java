package net.dermif1.maren.datagen;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.spongepowered.asm.mixin.Mixin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MixinJsonProvider implements DataProvider {

    private final PackOutput output;
    private final String modId;
    private final String mixinPackage;
    private final Path mixinSourceDir;

    public MixinJsonProvider(PackOutput output, String modId,
                             String mixinPackage, Path mixinSourceDir) {
        this.output = output;
        this.modId = modId;
        this.mixinPackage = mixinPackage;
        this.mixinSourceDir = mixinSourceDir;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<String> mixins = new ArrayList<>();
        List<String> clientMixins = new ArrayList<>();

        try {
            Files.walk(mixinSourceDir)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(p -> {
                        try {
                            String content = Files.readString(p);
                            if (content.contains("@Mixin(")) {
                                String className = p.getFileName()
                                        .toString().replace(".java", "");
                                if (content.contains("Dist.CLIENT")
                                        || content.contains("@OnlyIn")) {
                                    clientMixins.add(className);
                                } else {
                                    mixins.add(className);
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Не вдалось сканувати mixin папку", e);
        }

        mixins.sort(String::compareTo);
        clientMixins.sort(String::compareTo);

        JsonObject json = new JsonObject();
        json.addProperty("required", true);
        json.addProperty("minVersion", "0.8");
        json.addProperty("package", mixinPackage);
        json.addProperty("compatibilityLevel", "JAVA_21");

        JsonArray mixinsArray = new JsonArray();
        mixins.forEach(mixinsArray::add);
        json.add("mixins", mixinsArray);

        JsonArray clientArray = new JsonArray();
        clientMixins.forEach(clientArray::add);
        json.add("client", clientArray);

        JsonObject injectors = new JsonObject();
        injectors.addProperty("defaultRequire", 1);
        json.add("injectors", injectors);

        String jsonStr = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(json);

        // ✅ Записуємо напряму у src/main/resources/ минаючи HashCache
        Path outputPath = mixinSourceDir
                .getParent()                          // net/dermif1/maren/
                .getParent()                          // net/dermif1/
                .getParent()                          // net/
                .getParent()                          // java/
                .getParent()                          // main/
                .resolveSibling("resources")          // main/../resources = resources/
                .resolve(modId + ".mixins.json");

        return CompletableFuture.runAsync(() -> {
            try {
                Files.createDirectories(outputPath.getParent());
                Files.writeString(outputPath, jsonStr);
                System.out.println("✅ " + modId + ".mixins.json оновлено!");
                System.out.println("   Mixins: " + mixins);
                System.out.println("   Client: " + clientMixins);
            } catch (IOException e) {
                throw new RuntimeException("Не вдалось записати mixins.json", e);
            }
        });
    }

    @Override
    public String getName() {
        return "Mixin JSON: " + modId;
    }
}