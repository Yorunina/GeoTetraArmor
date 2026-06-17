package ovo.yiran.geotetraarmor.model;

import net.minecraft.resources.ResourceLocation;
import ovo.yiran.geotetraarmor.items.ModularArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class ModularArmorModel<T extends ModularArmorItem> extends GeoModel<T> {
    private ResourceLocation modelPath;
    private ResourceLocation texturePath;
    private ResourceLocation animationsPath;

    public ModularArmorModel(GeoModuleModelData moduleModel) {
        modelPath = buildFormattedModelPath(moduleModel.getLocation());
        texturePath = buildFormattedTexturePath(moduleModel.getLocation());
        animationsPath = buildFormattedAnimationPath(moduleModel.getLocation());
    }

    @Override
    public ResourceLocation getModelResource(T t) {
        return modelPath;
    }

    @Override
    public ResourceLocation getTextureResource(T t) {
        return texturePath;
    }

    @Override
    public ResourceLocation getAnimationResource(T t) {
        return animationsPath;
    }

    public ResourceLocation buildFormattedModelPath(ResourceLocation basePath) {
        return new ResourceLocation(basePath.getNamespace(), "geo/" + basePath.getPath() + ".geo.json");
    }

    public ResourceLocation buildFormattedAnimationPath(ResourceLocation basePath) {
        return new ResourceLocation(basePath.getNamespace(), "animations/" + basePath.getPath() + ".animation.json");
    }

    public ResourceLocation buildFormattedTexturePath(ResourceLocation basePath) {
        return new ResourceLocation(basePath.getNamespace(), "textures/" + basePath.getPath() + ".png");
    }

}
