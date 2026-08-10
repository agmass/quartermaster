package org.agmas.client.render.animations;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.agmas.Quartermaster;
import org.agmas.client.render.animations.FlintlockAnimationState;

public class HandsModel extends Model<FlintlockAnimationState> {
	private final ModelPart main;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart gun;
	private final ModelPart ITEM;

	public HandsModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        this.main = root.getChild("main");
		this.leftarm = this.main.getChild("leftarm");
		this.rightarm = this.main.getChild("rightarm");
		this.gun = this.rightarm.getChild("gun");
		this.ITEM = this.gun.getChild("ITEM");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftarm = main.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -2.0F, 8.0F));

		PartDefinition rightarm = main.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.0F, -2.25F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -2.0F, 9.25F));

		PartDefinition gun = rightarm.addOrReplaceChild("gun", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, -1.75F));

		PartDefinition ITEM = gun.addOrReplaceChild("ITEM", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.5F, -2.5F, 1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

}