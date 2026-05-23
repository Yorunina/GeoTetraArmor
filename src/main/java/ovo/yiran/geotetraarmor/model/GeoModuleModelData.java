package ovo.yiran.geotetraarmor.model;

import com.mojang.math.Transformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import se.mickelus.mutil.gui.SimpleColor;
import se.mickelus.tetra.items.modular.ItemColors;
import se.mickelus.tetra.module.Priority;
import se.mickelus.tetra.module.data.MaterialData;
import se.mickelus.tetra.module.model.IModuleModel;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class GeoModuleModelData implements IModuleModel {
    public static final ResourceLocation TYPE = new ResourceLocation("tetra", "gecko");
    public ResourceLocation type;
    public ResourceLocation location;
    public ResourceLocation renderType;
    public Transformation transform;
    public int emission = 0;
    public SimpleColor tint = new SimpleColor(-1);
    public SimpleColor overlayTint;
    public Priority renderLayer;
    public boolean invertPerspectives;
    public ItemDisplayContext[] contexts;

    public GeoModuleModelData() {
        super();
    }

    public GeoModuleModelData(ResourceLocation location) {
        this(TYPE, location, null, null, 0, null, null, null, false, null);
    }

    public GeoModuleModelData(ResourceLocation type, ResourceLocation location, ResourceLocation renderType, Transformation transform, Integer emission, SimpleColor tint, SimpleColor overlayTint, Priority renderLayer, Boolean invertPerspectives, ItemDisplayContext[] contexts) {
        this();
        this.type = type;
        this.location = location;
        this.renderType = renderType;
        this.transform = transform;
        if (emission != null) {
            this.emission = Mth.clamp(0, emission, 15);
        }

        if (tint != null) {
            this.tint = tint;
        }

        if (overlayTint != null) {
            this.overlayTint = overlayTint;
        }

        if (renderLayer != null) {
            this.renderLayer = renderLayer;
        }

        if (invertPerspectives != null) {
            this.invertPerspectives = invertPerspectives;
        }
        this.contexts = contexts;
    }


    @Override
    public ResourceLocation getType() {
        return this.type;
    }

    public ResourceLocation getLocation() {
        return this.location;
    }

    @Override
    public Priority getRenderLayer() {
        return this.renderLayer;
    }

    @Override
    public GeoModuleModelData forMaterial(List<String> availableTextures, MaterialData material) {
        if (Arrays.stream(material.textureOverrides).anyMatch((override) -> this.location.getPath().equals(override))) {
            GeoModuleModelData copy = this.copy();
            copy.location = appendString(this.location, material.textures[0]);
            copy.tint = material.tintOverrides ? new SimpleColor(material.tints.texture) : new SimpleColor(-1);
            copy.overlayTint = new SimpleColor(material.tints.texture);
            return copy;
        } else {
            Stream<String> materialTextures = Arrays.stream(material.textures);
            Objects.requireNonNull(availableTextures);
            ResourceLocation updatedLocation = (ResourceLocation)materialTextures
                    .filter(availableTextures::contains)
                    .findFirst()
                    .map((texture) -> appendString(this.location, texture))
                    .orElseGet(() -> appendString(this.location, availableTextures.get(0)));
            GeoModuleModelData copy = this.copy();
            copy.location = updatedLocation;
            copy.tint = new SimpleColor(material.tints.texture);
            copy.overlayTint = new SimpleColor(material.tints.texture);
            return copy;
        }
    }

    protected static ResourceLocation appendString(ResourceLocation resourceLocation, String string) {
        return new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + string);
    }

    @Override
    public GeoModuleModelData withSlotSuffix(String suffix) {
        GeoModuleModelData copy = this.copy();
        String var10003 = this.location.getNamespace();
        String var10004 = this.location.getPath();
        copy.location = new ResourceLocation(var10003, var10004 + suffix);
        return copy;
    }

    @Override
    public GeoModuleModelData inheritTint(SimpleColor parentTint) {
        if (ItemColors.inherit == this.tint.getRaw()) {
            GeoModuleModelData copy = this.copy();
            copy.tint = parentTint;
            return copy;
        } else {
            return this;
        }
    }

    @Override
    public SimpleColor getOverlayTint() {
        return this.overlayTint;
    }

    public GeoModuleModelData copy() {
        return new GeoModuleModelData(this.type, this.location, this.renderType, this.transform, this.emission, this.tint, this.overlayTint, this.renderLayer, this.invertPerspectives, this.contexts);
    }
}
