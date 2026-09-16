package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


public class ModelNastysaurus extends ModelBase
{
	private float wingspeed = 1.0F;
	ModelRenderer lclaw1;
	ModelRenderer body;
	ModelRenderer leftleg1;
	ModelRenderer tail1;
	ModelRenderer leftleg2;
	ModelRenderer body2;
	ModelRenderer leftleg3;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer lclaw2;
	ModelRenderer lclaw3;
	ModelRenderer lclaw4;
	ModelRenderer lclaw5;
	ModelRenderer lclaw6;
	ModelRenderer lclaw7;
	ModelRenderer neck3;
	ModelRenderer head3;
	ModelRenderer jaw1;
	ModelRenderer tooth1;
	ModelRenderer tooth2;
	ModelRenderer tooth3;
	ModelRenderer tooth4;
	ModelRenderer tooth5;
	ModelRenderer jaw5;
	ModelRenderer head7;
	ModelRenderer tooth6;
	ModelRenderer tooth7;
	ModelRenderer tooth8;
	ModelRenderer tooth9;
	ModelRenderer tooth10;
	ModelRenderer tooth11;
	ModelRenderer tooth12;
	ModelRenderer tooth13;
	ModelRenderer rightleg1;
	ModelRenderer rightleg2;
	ModelRenderer tooth14;
	ModelRenderer tooth15;
	ModelRenderer tooth16;
	ModelRenderer tooth17;
	ModelRenderer tooth18;
	ModelRenderer tooth19;
	ModelRenderer tooth20;
	ModelRenderer tooth21;
	ModelRenderer tooth22;
	ModelRenderer tooth23;
	ModelRenderer rightleg3;
	ModelRenderer rclaw2;
	ModelRenderer rclaw4;
	ModelRenderer rclaw1;
	ModelRenderer rclaw5;
	ModelRenderer rclaw7;
	ModelRenderer rclaw3;
	ModelRenderer rclaw6;
	ModelRenderer neck1;
	ModelRenderer neck2;
	ModelRenderer tail4;
	ModelRenderer Spike1;
	ModelRenderer Spike2;
	ModelRenderer Spike3;

	
	public ModelNastysaurus(float f1)
	{
		
		this.wingspeed = f1;
		
		this.textureWidth = 512;
		this.textureHeight = 256;
		
		this.lclaw1 = new ModelRenderer(this, 300, 111);
		this.lclaw1.addBox(-3.0F, 0.0F, -3.0F, 2, 3, 6);
		this.lclaw1.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw1.setTextureSize(512, 256);
		this.lclaw1.mirror = true;
		this.setRotation(this.lclaw1, 0.0F, 0.6632251F, 0.0F);
		this.body = new ModelRenderer(this, 407, 3);
		this.body.addBox(-6.0F, -12.0F, -9.0F, 12, 17, 9);
		this.body.setRotationPoint(0.0F, -2.0F, 9.0F);
		this.body.setTextureSize(512, 256);
		this.body.mirror = true;
		this.setRotation(this.body, -0.3141593F, 0.0F, 0.0F);
		this.leftleg1 = new ModelRenderer(this, 300, 0);
		this.leftleg1.addBox(-3.0F, -4.0F, -21.0F, 6, 11, 11);
		this.leftleg1.setRotationPoint(9.0F, 2.0F, 26.0F);
		this.leftleg1.setTextureSize(512, 256);
		this.leftleg1.mirror = true;
		this.setRotation(this.leftleg1, -0.5759587F, 0.0F, 0.0F);
		this.tail1 = new ModelRenderer(this, 400, 75);
		this.tail1.addBox(-6.0F, -6.0F, 0.0F, 10, 12, 14);
		this.tail1.setRotationPoint(1.0F, -5.0F, 22.0F);
		this.tail1.setTextureSize(512, 256);
		this.tail1.mirror = true;
		this.setRotation(this.tail1, -0.1745329F, 0.0F, 0.0F);
		this.leftleg2 = new ModelRenderer(this, 300, 23);
		this.leftleg2.addBox(-3.0F, -10.0F, -5.0F, 5, 13, 7);
		this.leftleg2.setRotationPoint(9.0F, 2.0F, 26.0F);
		this.leftleg2.setTextureSize(512, 256);
		this.leftleg2.mirror = true;
		this.setRotation(this.leftleg2, 0.9773844F, 0.0F, 0.0F);
		this.body2 = new ModelRenderer(this, 400, 39);
		this.body2.addBox(0.0F, -3.0F, -3.0F, 12, 18, 16);
		this.body2.setRotationPoint(-6.0F, -11.0F, 10.0F);
		this.body2.setTextureSize(512, 256);
		this.body2.mirror = true;
		this.setRotation(this.body2, -0.1047198F, 0.0F, 0.0F);
		this.leftleg3 = new ModelRenderer(this, 300, 51);
		this.leftleg3.addBox(-1.0F, -19.0F, 0.0F, 4, 18, 6);
		this.leftleg3.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.leftleg3.setTextureSize(512, 256);
		this.leftleg3.mirror = true;
		this.setRotation(this.leftleg3, (-(float)Math.PI / 6F), 0.0F, 0.0F);
		this.tail2 = new ModelRenderer(this, 400, 103);
		this.tail2.addBox(-4.0F, -4.0F, 0.0F, 8, 10, 12);
		this.tail2.setRotationPoint(0.0F, -4.0F, 35.0F);
		this.tail2.setTextureSize(512, 256);
		this.tail2.mirror = true;
		this.setRotation(this.tail2, -0.1396263F, 0.0F, 0.0F);
		this.tail3 = new ModelRenderer(this, 400, 127);
		this.tail3.addBox(-3.0F, -3.0F, 0.0F, 6, 8, 12);
		this.tail3.setRotationPoint(0.0F, -3.0F, 46.0F);
		this.tail3.setTextureSize(512, 256);
		this.tail3.mirror = true;
		this.setRotation(this.tail3, -0.1396263F, 0.0F, 0.0F);
		this.lclaw2 = new ModelRenderer(this, 300, 76);
		this.lclaw2.addBox(-1.0F, -1.0F, -6.0F, 4, 4, 13);
		this.lclaw2.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw2.setTextureSize(512, 256);
		this.lclaw2.mirror = true;
		this.setRotation(this.lclaw2, 0.0F, 0.0F, 0.0F);
		this.lclaw3 = new ModelRenderer(this, 300, 95);
		this.lclaw3.addBox(2.0F, 0.0F, -6.0F, 2, 3, 10);
		this.lclaw3.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw3.setTextureSize(512, 256);
		this.lclaw3.mirror = true;
		this.setRotation(this.lclaw3, 0.0F, -0.6632251F, 0.0F);
		this.lclaw4 = new ModelRenderer(this, 308, 123);
		this.lclaw4.addBox(0.0F, 0.0F, -10.0F, 2, 3, 4);
		this.lclaw4.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw4.setTextureSize(512, 256);
		this.lclaw4.mirror = true;
		this.setRotation(this.lclaw4, 0.0F, 0.0F, 0.0F);
		this.lclaw5 = new ModelRenderer(this, 300, 123);
		this.lclaw5.addBox(-2.5F, 1.0F, -5.0F, 1, 2, 2);
		this.lclaw5.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw5.setTextureSize(512, 256);
		this.lclaw5.mirror = true;
		this.setRotation(this.lclaw5, 0.0F, 0.6632251F, 0.0F);
		this.lclaw6 = new ModelRenderer(this, 322, 123);
		this.lclaw6.addBox(2.5F, 1.0F, -9.0F, 1, 2, 3);
		this.lclaw6.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw6.setTextureSize(512, 256);
		this.lclaw6.mirror = true;
		this.setRotation(this.lclaw6, 0.0F, -0.6632251F, 0.0F);
		this.lclaw7 = new ModelRenderer(this, 333, 123);
		this.lclaw7.addBox(0.0F, 1.0F, 7.0F, 1, 2, 3);
		this.lclaw7.setRotationPoint(7.0F, 21.0F, 11.0F);
		this.lclaw7.setTextureSize(512, 256);
		this.lclaw7.mirror = true;
		this.setRotation(this.lclaw7, 0.0F, 0.0F, 0.0F);
		this.neck3 = new ModelRenderer(this, 375, 23);
		this.neck3.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 8);
		this.neck3.setRotationPoint(0.0F, -24.0F, -9.0F);
		this.neck3.setTextureSize(512, 256);
		this.neck3.mirror = true;
		this.setRotation(this.neck3, -0.2443461F, 0.0F, 0.0F);
		this.head3 = new ModelRenderer(this, 130, 32);
		this.head3.addBox(-3.0F, -6.0F, -15.0F, 6, 6, 17);
		this.head3.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.head3.setTextureSize(512, 256);
		this.head3.mirror = true;
		this.setRotation(this.head3, -0.2443461F, 0.0F, 0.0F);
		this.jaw1 = new ModelRenderer(this, 143, 114);
		this.jaw1.addBox(-3.0F, 1.0F, -14.0F, 6, 3, 15);
		this.jaw1.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.jaw1.setTextureSize(512, 256);
		this.jaw1.mirror = true;
		this.setRotation(this.jaw1, 0.1919862F, 0.0F, 0.0F);
		this.tooth1 = new ModelRenderer(this, 0, 0);
		this.tooth1.addBox(-3.0F, 0.0F, -14.0F, 1, 3, 1);
		this.tooth1.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth1.setTextureSize(512, 256);
		this.tooth1.mirror = true;
		this.setRotation(this.tooth1, -0.2443461F, 0.0F, 0.0F);
		this.tooth2 = new ModelRenderer(this, 0, 0);
		this.tooth2.addBox(-0.5F, 0.0F, -14.0F, 1, 2, 1);
		this.tooth2.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth2.setTextureSize(512, 256);
		this.tooth2.mirror = true;
		this.setRotation(this.tooth2, -0.2443461F, 0.0F, 0.0F);
		this.tooth3 = new ModelRenderer(this, 0, 0);
		this.tooth3.addBox(2.0F, 0.0F, -14.0F, 1, 3, 1);
		this.tooth3.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth3.setTextureSize(512, 256);
		this.tooth3.mirror = true;
		this.setRotation(this.tooth3, -0.2443461F, 0.0F, 0.0F);
		this.tooth4 = new ModelRenderer(this, 0, 0);
		this.tooth4.addBox(-2.0F, 0.0F, -12.0F, 1, 3, 1);
		this.tooth4.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth4.setTextureSize(512, 256);
		this.tooth4.mirror = true;
		this.setRotation(this.tooth4, -0.2443461F, 0.0F, 0.0F);
		this.tooth5 = new ModelRenderer(this, 0, 0);
		this.tooth5.addBox(1.0F, 0.0F, -12.0F, 1, 3, 1);
		this.tooth5.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth5.setTextureSize(512, 256);
		this.tooth5.mirror = true;
		this.setRotation(this.tooth5, -0.2443461F, 0.0F, 0.0F);
		this.jaw5 = new ModelRenderer(this, 151, 135);
		this.jaw5.addBox(-4.0F, 1.0F, -4.0F, 8, 4, 7);
		this.jaw5.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.jaw5.setTextureSize(512, 256);
		this.jaw5.mirror = true;
		this.setRotation(this.jaw5, 0.1919862F, 0.0F, 0.0F);
		this.head7 = new ModelRenderer(this, 185, 34);
		this.head7.addBox(-4.0F, -7.0F, -3.0F, 8, 7, 10);
		this.head7.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.head7.setTextureSize(512, 256);
		this.head7.mirror = true;
		this.setRotation(this.head7, -0.2443461F, 0.0F, 0.0F);
		this.tooth6 = new ModelRenderer(this, 0, 0);
		this.tooth6.addBox(-3.0F, 0.0F, -10.0F, 1, 2, 1);
		this.tooth6.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth6.setTextureSize(512, 256);
		this.tooth6.mirror = true;
		this.setRotation(this.tooth6, -0.2443461F, 0.0F, 0.0F);
		this.tooth7 = new ModelRenderer(this, 0, 0);
		this.tooth7.addBox(2.0F, 0.0F, -10.0F, 1, 2, 1);
		this.tooth7.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth7.setTextureSize(512, 256);
		this.tooth7.mirror = true;
		this.setRotation(this.tooth7, -0.2443461F, 0.0F, 0.0F);
		this.tooth8 = new ModelRenderer(this, 0, 0);
		this.tooth8.addBox(-2.0F, 0.0F, -8.0F, 1, 2, 1);
		this.tooth8.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth8.setTextureSize(512, 256);
		this.tooth8.mirror = true;
		this.setRotation(this.tooth8, -0.2443461F, 0.0F, 0.0F);
		this.tooth9 = new ModelRenderer(this, 0, 0);
		this.tooth9.addBox(1.0F, 0.0F, -8.0F, 1, 2, 1);
		this.tooth9.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth9.setTextureSize(512, 256);
		this.tooth9.mirror = true;
		this.setRotation(this.tooth9, -0.2443461F, 0.0F, 0.0F);
		this.tooth10 = new ModelRenderer(this, 0, 0);
		this.tooth10.addBox(-3.0F, 0.0F, -6.0F, 1, 2, 1);
		this.tooth10.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth10.setTextureSize(512, 256);
		this.tooth10.mirror = true;
		this.setRotation(this.tooth10, -0.2443461F, 0.0F, 0.0F);
		this.tooth11 = new ModelRenderer(this, 0, 0);
		this.tooth11.addBox(2.0F, 0.0F, -6.0F, 1, 2, 1);
		this.tooth11.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth11.setTextureSize(512, 256);
		this.tooth11.mirror = true;
		this.setRotation(this.tooth11, -0.2443461F, 0.0F, 0.0F);
		this.tooth12 = new ModelRenderer(this, 0, 0);
		this.tooth12.addBox(-2.0F, 0.0F, -4.0F, 1, 1, 1);
		this.tooth12.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth12.setTextureSize(512, 256);
		this.tooth12.mirror = true;
		this.setRotation(this.tooth12, -0.2443461F, 0.0F, 0.0F);
		this.tooth13 = new ModelRenderer(this, -1, 0);
		this.tooth13.addBox(1.0F, 0.0F, -4.0F, 1, 1, 1);
		this.tooth13.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth13.setTextureSize(512, 256);
		this.tooth13.mirror = true;
		this.setRotation(this.tooth13, -0.2443461F, 0.0F, 0.0F);
		this.rightleg1 = new ModelRenderer(this, 246, 0);
		this.rightleg1.addBox(-2.0F, -4.0F, -21.0F, 6, 11, 11);
		this.rightleg1.setRotationPoint(-10.0F, 2.0F, 26.0F);
		this.rightleg1.setTextureSize(512, 256);
		this.rightleg1.mirror = true;
		this.setRotation(this.rightleg1, -0.5934119F, 0.0F, 0.0F);
		this.rightleg2 = new ModelRenderer(this, 250, 24);
		this.rightleg2.addBox(-1.0F, -10.0F, -5.0F, 5, 13, 7);
		this.rightleg2.setRotationPoint(-10.0F, 2.0F, 26.0F);
		this.rightleg2.setTextureSize(512, 256);
		this.rightleg2.mirror = true;
		this.setRotation(this.rightleg2, 0.9773844F, 0.0F, 0.0F);
		this.tooth14 = new ModelRenderer(this, 0, 0);
		this.tooth14.addBox(0.5F, -2.0F, -14.0F, 1, 3, 1);
		this.tooth14.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth14.setTextureSize(512, 256);
		this.tooth14.mirror = true;
		this.setRotation(this.tooth14, 0.1919862F, 0.0F, 0.0F);
		this.tooth15 = new ModelRenderer(this, 0, 0);
		this.tooth15.addBox(-1.5F, -2.0F, -14.0F, 1, 3, 1);
		this.tooth15.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth15.setTextureSize(512, 256);
		this.tooth15.mirror = true;
		this.setRotation(this.tooth15, 0.1919862F, 0.0F, 0.0F);
		this.tooth16 = new ModelRenderer(this, 0, 0);
		this.tooth16.addBox(2.0F, -1.0F, -12.0F, 1, 2, 1);
		this.tooth16.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth16.setTextureSize(512, 256);
		this.tooth16.mirror = true;
		this.setRotation(this.tooth16, 0.1919862F, 0.0F, 0.0F);
		this.tooth17 = new ModelRenderer(this, 0, 0);
		this.tooth17.addBox(-3.0F, -1.0F, -12.0F, 1, 2, 1);
		this.tooth17.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth17.setTextureSize(512, 256);
		this.tooth17.mirror = true;
		this.setRotation(this.tooth17, 0.1919862F, 0.0F, 0.0F);
		this.tooth18 = new ModelRenderer(this, 0, 0);
		this.tooth18.addBox(1.0F, -1.0F, -10.0F, 1, 2, 1);
		this.tooth18.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth18.setTextureSize(512, 256);
		this.tooth18.mirror = true;
		this.setRotation(this.tooth18, 0.1919862F, 0.0F, 0.0F);
		this.tooth19 = new ModelRenderer(this, 0, 0);
		this.tooth19.addBox(-2.0F, -1.0F, -10.0F, 1, 2, 1);
		this.tooth19.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth19.setTextureSize(512, 256);
		this.tooth19.mirror = true;
		this.setRotation(this.tooth19, 0.1919862F, 0.0F, 0.0F);
		this.tooth20 = new ModelRenderer(this, 0, 0);
		this.tooth20.addBox(-3.0F, -1.0F, -8.0F, 1, 2, 1);
		this.tooth20.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth20.setTextureSize(512, 256);
		this.tooth20.mirror = true;
		this.setRotation(this.tooth20, 0.1919862F, 0.0F, 0.0F);
		this.tooth21 = new ModelRenderer(this, 0, 0);
		this.tooth21.addBox(2.0F, -1.0F, -8.0F, 1, 2, 1);
		this.tooth21.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth21.setTextureSize(512, 256);
		this.tooth21.mirror = true;
		this.setRotation(this.tooth21, 0.1919862F, 0.0F, 0.0F);
		this.tooth22 = new ModelRenderer(this, 0, 0);
		this.tooth22.addBox(1.0F, 0.0F, -6.0F, 1, 1, 1);
		this.tooth22.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth22.setTextureSize(512, 256);
		this.tooth22.mirror = true;
		this.setRotation(this.tooth22, 0.1919862F, 0.0F, 0.0F);
		this.tooth23 = new ModelRenderer(this, 0, 0);
		this.tooth23.addBox(-2.0F, 0.0F, -6.0F, 1, 1, 1);
		this.tooth23.setRotationPoint(0.0F, -26.0F, -14.0F);
		this.tooth23.setTextureSize(512, 256);
		this.tooth23.mirror = true;
		this.setRotation(this.tooth23, 0.1919862F, 0.0F, 0.0F);
		this.rightleg3 = new ModelRenderer(this, 250, 47);
		this.rightleg3.addBox(-2.0F, -19.0F, 0.0F, 4, 18, 6);
		this.rightleg3.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rightleg3.setTextureSize(512, 256);
		this.rightleg3.mirror = true;
		this.setRotation(this.rightleg3, (-(float)Math.PI / 6F), 0.0F, 0.0F);
		this.rclaw2 = new ModelRenderer(this, 250, 76);
		this.rclaw2.addBox(-2.0F, -1.0F, -6.0F, 4, 4, 13);
		this.rclaw2.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw2.setTextureSize(512, 256);
		this.rclaw2.mirror = true;
		this.setRotation(this.rclaw2, 0.0F, 0.0F, 0.0F);
		this.rclaw4 = new ModelRenderer(this, 247, 123);
		this.rclaw4.addBox(-1.0F, 0.0F, -10.0F, 2, 3, 4);
		this.rclaw4.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw4.setTextureSize(512, 256);
		this.rclaw4.mirror = true;
		this.setRotation(this.rclaw4, 0.0F, 0.0F, 0.0F);
		this.rclaw1 = new ModelRenderer(this, 250, 111);
		this.rclaw1.addBox(2.0F, 0.0F, -3.0F, 2, 3, 6);
		this.rclaw1.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw1.setTextureSize(512, 256);
		this.rclaw1.mirror = true;
		this.setRotation(this.rclaw1, 0.0F, -0.6632251F, 0.0F);
		this.rclaw5 = new ModelRenderer(this, 261, 123);
		this.rclaw5.addBox(2.5F, 1.0F, -5.0F, 1, 2, 2);
		this.rclaw5.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw5.setTextureSize(512, 256);
		this.rclaw5.mirror = true;
		this.setRotation(this.rclaw5, 0.0F, -0.6632251F, 0.0F);
		this.rclaw7 = new ModelRenderer(this, 283, 123);
		this.rclaw7.addBox(0.0F, 1.0F, 7.0F, 1, 2, 3);
		this.rclaw7.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw7.setTextureSize(512, 256);
		this.rclaw7.mirror = true;
		this.setRotation(this.rclaw7, 0.0F, 0.0F, 0.0F);
		this.rclaw3 = new ModelRenderer(this, 250, 95);
		this.rclaw3.addBox(-3.0F, 0.0F, -6.0F, 2, 3, 10);
		this.rclaw3.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw3.setTextureSize(512, 256);
		this.rclaw3.mirror = true;
		this.setRotation(this.rclaw3, 0.0F, 0.6632251F, 0.0F);
		this.rclaw6 = new ModelRenderer(this, 270, 123);
		this.rclaw6.addBox(-2.5F, 1.0F, -9.0F, 1, 2, 3);
		this.rclaw6.setRotationPoint(-8.0F, 21.0F, 11.0F);
		this.rclaw6.setTextureSize(512, 256);
		this.rclaw6.mirror = true;
		this.setRotation(this.rclaw6, 0.0F, 0.6632251F, 0.0F);
		this.neck1 = new ModelRenderer(this, 45, 0);
		this.neck1.addBox(-5.0F, -6.0F, -14.0F, 10, 12, 15);
		this.neck1.setRotationPoint(0.0F, -9.0F, 5.0F);
		this.neck1.setTextureSize(512, 256);
		this.neck1.mirror = true;
		this.setRotation(this.neck1, -0.837758F, 0.0F, 0.0F);
		this.neck2 = new ModelRenderer(this, 48, 29);
		this.neck2.addBox(-4.5F, -4.0F, -10.0F, 9, 9, 10);
		this.neck2.setRotationPoint(0.0F, -19.0F, -2.0F);
		this.neck2.setTextureSize(512, 256);
		this.neck2.mirror = true;
		this.setRotation(this.neck2, (-(float)Math.PI / 4F), 0.0F, 0.0F);
		this.tail4 = new ModelRenderer(this, 400, 150);
		this.tail4.addBox(-2.0F, -3.0F, 0.0F, 4, 6, 16);
		this.tail4.setRotationPoint(0.0F, -1.0F, 56.0F);
		this.tail4.setTextureSize(512, 256);
		this.tail4.mirror = true;
		this.setRotation(this.tail4, -0.1396263F, 0.0F, 0.0F);
		this.Spike1 = new ModelRenderer(this, 0, 100);
		this.Spike1.addBox(-2.0F, -16.0F, -1.0F, 4, 16, 18);
		this.Spike1.setRotationPoint(0.0F, -4.0F, 7.0F);
		this.Spike1.setTextureSize(512, 256);
		this.Spike1.mirror = true;
		this.setRotation(this.Spike1, 0.5061455F, 0.0F, 0.0F);
		this.Spike2 = new ModelRenderer(this, 0, 72);
		this.Spike2.addBox(-1.5F, -12.0F, 0.0F, 3, 12, 10);
		this.Spike2.setRotationPoint(0.0F, 0.0F, 29.0F);
		this.Spike2.setTextureSize(512, 256);
		this.Spike2.mirror = true;
		this.setRotation(this.Spike2, 0.4886922F, 0.0F, 0.0F);
		this.Spike3 = new ModelRenderer(this, 0, 44);
		this.Spike3.addBox(-1.0F, -7.0F, 0.0F, 2, 8, 7);
		this.Spike3.setRotationPoint(0.0F, -2.0F, 41.0F);
		this.Spike3.setTextureSize(512, 256);
		this.Spike3.mirror = true;
		this.setRotation(this.Spike3, 0.5934119F, 0.0F, 0.0F);
	}

	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		Nastysaurus e = (Nastysaurus)entity;
		RenderInfo r = null;
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float newangle = 0.0F;
		float pscale = 2.0F;
		float tailspeed = 0.0F, tailamp = 0.0F;
		
		float clawZ = 15.0F;
		float clawY = 21.0F;
		float clawZamp = 5.0F * pscale;
		float clawYamp = 2.0F * pscale;
		float t1, t2;
		float pi4 = ((float)Math.PI / 4F);
		
		r = e.getRenderInfo();
		
		
		
		
		f3 %= 360.0F;
		
		f3 = f3 = f3 * 0.35F;
		
		this.neck2.rotateAngleY = (float)Math.toRadians((double)f3) * 0.5F;
		this.head3.rotateAngleY = (float)Math.toRadians((double)f3);
		this.neck3.rotateAngleY = this.head7.rotateAngleY = this.head3.rotateAngleY;
		this.jaw1.rotateAngleY = this.jaw5.rotateAngleY = this.head3.rotateAngleY;
		this.tooth1.rotateAngleY = this.tooth2.rotateAngleY = this.tooth3.rotateAngleY = this.tooth4.rotateAngleY = this.tooth5.rotateAngleY = this.head3.rotateAngleY;
		this.tooth6.rotateAngleY = this.tooth7.rotateAngleY = this.tooth8.rotateAngleY = this.tooth9.rotateAngleY = this.tooth10.rotateAngleY = this.head3.rotateAngleY;
		this.tooth11.rotateAngleY = this.tooth12.rotateAngleY = this.tooth13.rotateAngleY = this.tooth14.rotateAngleY = this.tooth15.rotateAngleY = this.head3.rotateAngleY;
		this.tooth16.rotateAngleY = this.tooth17.rotateAngleY = this.tooth18.rotateAngleY = this.tooth19.rotateAngleY = this.tooth20.rotateAngleY = this.head3.rotateAngleY;
		this.tooth21.rotateAngleY = this.tooth22.rotateAngleY = this.tooth23.rotateAngleY = this.head3.rotateAngleY;
		
		
		
		
		
		if (e.getAttacking() != 0) {
			newangle = MathHelper.cos(f2 * 0.85F * this.wingspeed) * (float)Math.PI * 0.16F;
			newangle += 0.5F;
		} else {
			newangle = f2 * 0.7F * this.wingspeed % ((float)Math.PI * 2F);
			newangle = Math.abs(newangle);
			
			if (newangle < r.rf1)
			{
				r.ri1 = 0;
				if (e.worldObj.rand.nextInt(20) == 1) r.ri1 |= 1;
			}

			r.rf1 = newangle;
			
			if (r.ri1 != 0) {
				newangle = MathHelper.sin(f2 * 0.85F * this.wingspeed) * (float)Math.PI * 0.16F;
				newangle += 0.5F;
			} else {
				newangle = pi4 / 4.0F;
			}
		}

		this.jaw1.rotateAngleX = this.jaw5.rotateAngleX = newangle;
		this.tooth14.rotateAngleX = this.tooth15.rotateAngleX = newangle;
		this.tooth16.rotateAngleX = this.tooth17.rotateAngleX = this.tooth18.rotateAngleX = this.tooth19.rotateAngleX = this.tooth20.rotateAngleX = newangle;
		this.tooth21.rotateAngleX = this.tooth22.rotateAngleX = this.tooth23.rotateAngleX = newangle;
		
		
		
		
		
		
		
		t1 = 0.0F;
		t2 = 0.0F;
		
		if ((double)f1 > 0.001) {
			newangle = MathHelper.cos(f2 * this.wingspeed / pscale);
			t1 = MathHelper.sin(f2 * this.wingspeed / pscale);
		} else {
			newangle = 0.0F;
			t1 = 0.0F;
			t2 = 0.0F;
		}
		if (t1 > 0.0F) {
			t2 = t1 * clawYamp * f1;
			this.lclaw1.rotationPointY = clawY - t2;
		} else {
			this.lclaw1.rotationPointY = clawY;
		}

		this.lclaw1.rotationPointZ = clawZ + clawZamp * newangle * f1;
		this.lclaw2.rotationPointZ = this.lclaw3.rotationPointZ = this.lclaw4.rotationPointZ = this.lclaw5.rotationPointZ = this.lclaw6.rotationPointZ = this.lclaw7.rotationPointZ = this.lclaw1.rotationPointZ;
		this.lclaw2.rotationPointY = this.lclaw3.rotationPointY = this.lclaw4.rotationPointY = this.lclaw5.rotationPointY = this.lclaw6.rotationPointY = this.lclaw7.rotationPointY = this.lclaw1.rotationPointY;
		
		this.leftleg3.rotationPointZ = this.lclaw1.rotationPointZ;
		this.leftleg3.rotationPointY = this.lclaw1.rotationPointY;
		this.leftleg3.rotateAngleX = -0.523F + newangle * (float)Math.PI * 0.15F * f1;
		
		
		this.leftleg1.rotateAngleX = -0.576F + newangle * (float)Math.PI * 0.06F * f1;
		this.leftleg2.rotateAngleX = 0.977F + newangle * (float)Math.PI * 0.06F * f1;
		this.leftleg1.rotationPointY = this.leftleg2.rotationPointY = this.leftleg3.rotationPointY - (float)Math.cos((double)this.leftleg3.rotateAngleX) * 17.0F;
		this.leftleg1.rotationPointZ = this.leftleg2.rotationPointZ = this.leftleg3.rotationPointZ - (float)Math.sin((double)this.leftleg3.rotateAngleX) * 17.0F;
		
		t1 = 0.0F;
		t2 = 0.0F;
		if ((double)f1 > 0.001) {
			newangle = MathHelper.cos(f2 * this.wingspeed / pscale + pi4 * 4.0F);
			t1 = MathHelper.sin(f2 * this.wingspeed / pscale + pi4 * 4.0F);
		} else {
			newangle = 0.0F;
			t1 = 0.0F;
			t2 = 0.0F;
		}
		if (t1 > 0.0F) {
			t2 = t1 * clawYamp * f1;
			this.rclaw1.rotationPointY = clawY - t2;
		} else {
			this.rclaw1.rotationPointY = clawY;
		}
		this.rclaw1.rotationPointZ = clawZ + clawZamp * newangle * f1;
		this.rclaw2.rotationPointZ = this.rclaw3.rotationPointZ = this.rclaw4.rotationPointZ = this.rclaw5.rotationPointZ = this.rclaw6.rotationPointZ = this.rclaw7.rotationPointZ = this.rclaw1.rotationPointZ;
		this.rclaw2.rotationPointY = this.rclaw3.rotationPointY = this.rclaw4.rotationPointY = this.rclaw5.rotationPointY = this.rclaw6.rotationPointY = this.rclaw7.rotationPointY = this.rclaw1.rotationPointY;
		
		this.rightleg3.rotationPointZ = this.rclaw1.rotationPointZ;
		this.rightleg3.rotationPointY = this.rclaw1.rotationPointY;
		this.rightleg3.rotateAngleX = -0.523F + newangle * (float)Math.PI * 0.15F * f1;
		
		
		this.rightleg1.rotateAngleX = -0.576F + newangle * (float)Math.PI * 0.06F * f1;
		this.rightleg2.rotateAngleX = 0.977F + newangle * (float)Math.PI * 0.06F * f1;
		this.rightleg1.rotationPointY = this.rightleg2.rotationPointY = this.rightleg3.rotationPointY - (float)Math.cos((double)this.rightleg3.rotateAngleX) * 17.0F;
		this.rightleg1.rotationPointZ = this.rightleg2.rotationPointZ = this.rightleg3.rotationPointZ - (float)Math.sin((double)this.rightleg3.rotateAngleX) * 17.0F;
		
		
		
		
		
		this.lclaw2.rotateAngleX = this.lclaw3.rotateAngleX = this.lclaw4.rotateAngleX = this.lclaw5.rotateAngleX = this.lclaw6.rotateAngleX = this.lclaw7.rotateAngleX = this.lclaw1.rotateAngleX = 0.0F;
		this.rclaw2.rotateAngleX = this.rclaw3.rotateAngleX = this.rclaw4.rotateAngleX = this.rclaw5.rotateAngleX = this.rclaw6.rotateAngleX = this.rclaw7.rotateAngleX = this.rclaw1.rotateAngleX = 0.0F;
		
		
		if (e.getAttacking() != 0) {
			tailspeed = 0.76F;
			tailamp = 0.25F;
		} else {
			tailspeed = 0.26F;
			tailamp = 0.08F;
		}
		this.tail3.rotateAngleY = MathHelper.cos(f2 * tailspeed * this.wingspeed) * (float)Math.PI * tailamp / 2.0F;
		
		this.tail4.rotationPointZ = this.tail3.rotationPointZ + (float)Math.cos((double)this.tail3.rotateAngleY) * 11.0F;
		this.tail4.rotationPointX = this.tail3.rotationPointX + (float)Math.sin((double)this.tail3.rotateAngleY) * 11.0F;
		
		this.tail4.rotateAngleY = MathHelper.cos(f2 * tailspeed * this.wingspeed) * (float)Math.PI * tailamp;
		
		
		e.setRenderInfo(r);
		
		
		this.lclaw1.render(f5);
		this.body.render(f5);
		this.leftleg1.render(f5);
		this.tail1.render(f5);
		this.leftleg2.render(f5);
		this.body2.render(f5);
		this.leftleg3.render(f5);
		this.tail2.render(f5);
		this.tail3.render(f5);
		this.lclaw2.render(f5);
		this.lclaw3.render(f5);
		this.lclaw4.render(f5);
		this.lclaw5.render(f5);
		this.lclaw6.render(f5);
		this.lclaw7.render(f5);
		this.neck3.render(f5);
		this.head3.render(f5);
		this.jaw1.render(f5);
		this.tooth1.render(f5);
		this.tooth2.render(f5);
		this.tooth3.render(f5);
		this.tooth4.render(f5);
		this.tooth5.render(f5);
		this.jaw5.render(f5);
		this.head7.render(f5);
		this.tooth6.render(f5);
		this.tooth7.render(f5);
		this.tooth8.render(f5);
		this.tooth9.render(f5);
		this.tooth10.render(f5);
		this.tooth11.render(f5);
		this.tooth12.render(f5);
		this.tooth13.render(f5);
		this.rightleg1.render(f5);
		this.rightleg2.render(f5);
		this.tooth14.render(f5);
		this.tooth15.render(f5);
		this.tooth16.render(f5);
		this.tooth17.render(f5);
		this.tooth18.render(f5);
		this.tooth19.render(f5);
		this.tooth20.render(f5);
		this.tooth21.render(f5);
		this.tooth22.render(f5);
		this.tooth23.render(f5);
		this.rightleg3.render(f5);
		this.rclaw2.render(f5);
		this.rclaw4.render(f5);
		this.rclaw1.render(f5);
		this.rclaw5.render(f5);
		this.rclaw7.render(f5);
		this.rclaw3.render(f5);
		this.rclaw6.render(f5);
		this.neck1.render(f5);
		this.neck2.render(f5);
		this.tail4.render(f5);
		this.Spike1.render(f5);
		this.Spike2.render(f5);
		this.Spike3.render(f5);
		
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}
}
