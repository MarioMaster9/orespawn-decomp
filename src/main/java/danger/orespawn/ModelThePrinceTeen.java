package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;


public class ModelThePrinceTeen extends ModelBase
{
	private float wingspeed = 1.0F;
	ModelRenderer body;
	ModelRenderer leftleg1;
	ModelRenderer tail1;
	ModelRenderer leftleg2;
	ModelRenderer body2;
	ModelRenderer leftleg3;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer lclaw2;
	ModelRenderer lclaw4;
	ModelRenderer lclaw5;
	ModelRenderer lclaw6;
	ModelRenderer lclaw7;
	ModelRenderer tail4;
	ModelRenderer tail5;
	ModelRenderer neck1;
	ModelRenderer neck3;
	ModelRenderer head3;
	ModelRenderer jaw1;
	ModelRenderer jaw5;
	ModelRenderer head7;
	ModelRenderer rightleg1;
	ModelRenderer rightleg2;
	ModelRenderer rightleg3;
	ModelRenderer rclaw2;
	ModelRenderer rclaw4;
	ModelRenderer rclaw5;
	ModelRenderer rclaw7;
	ModelRenderer rclaw6;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer mem1;
	ModelRenderer mem2;
	ModelRenderer lshoulder;
	ModelRenderer rshoulder;
	ModelRenderer rwing1;
	ModelRenderer rmem1;
	ModelRenderer rwing2;
	ModelRenderer rmem2;
	ModelRenderer neck4;
	ModelRenderer neck5;
	ModelRenderer wing3;
	ModelRenderer mem3;
	ModelRenderer rwing3;
	ModelRenderer rmem3;
	ModelRenderer wing4;
	ModelRenderer mem4;
	ModelRenderer rwing4;
	ModelRenderer rmem4;
	ModelRenderer Tailspike1;
	ModelRenderer Tailspike2;
	ModelRenderer Tailspike3;
	ModelRenderer headfin;
	ModelRenderer backfin1;
	ModelRenderer backfin2;
	ModelRenderer neck3L;
	ModelRenderer neck4L;
	ModelRenderer neck3R;
	ModelRenderer neck4R;
	ModelRenderer neck5L;
	ModelRenderer neck5R;
	ModelRenderer jaw5L;
	ModelRenderer jaw5R;
	ModelRenderer head7L;
	ModelRenderer headfinL;
	ModelRenderer headfinR;
	ModelRenderer head7R;
	ModelRenderer jaw1L;
	ModelRenderer jaw1R;
	ModelRenderer head3L;
	ModelRenderer head3R;

	
	public ModelThePrinceTeen(float f1)
	{
		this.wingspeed = f1;
		
		this.textureWidth = 512;
		this.textureHeight = 256;
		
		this.body = new ModelRenderer(this, 400, 26);
		this.body.addBox(-12.5F, -12.0F, -9.0F, 25, 12, 9);
		this.body.setRotationPoint(0.0F, 0.0F, 9.0F);
		this.body.setTextureSize(512, 256);
		this.body.mirror = true;
		this.setRotation(this.body, 0.0698132F, 0.0F, 0.0F);
		this.leftleg1 = new ModelRenderer(this, 300, 10);
		this.leftleg1.addBox(-1.0F, -1.0F, -3.0F, 5, 9, 9);
		this.leftleg1.setRotationPoint(14.0F, -8.0F, 13.0F);
		this.leftleg1.setTextureSize(512, 256);
		this.leftleg1.mirror = true;
		this.setRotation(this.leftleg1, -0.5759587F, 0.0F, 0.0F);
		this.tail1 = new ModelRenderer(this, 400, 82);
		this.tail1.addBox(-9.0F, -6.0F, 0.0F, 18, 10, 12);
		this.tail1.setRotationPoint(0.0F, -3.0F, 22.0F);
		this.tail1.setTextureSize(512, 256);
		this.tail1.mirror = true;
		this.setRotation(this.tail1, -0.1745329F, 0.0F, 0.0F);
		this.leftleg2 = new ModelRenderer(this, 300, 31);
		this.leftleg2.addBox(-1.0F, 6.0F, -7.0F, 4, 12, 5);
		this.leftleg2.setRotationPoint(14.0F, -8.0F, 13.0F);
		this.leftleg2.setTextureSize(512, 256);
		this.leftleg2.mirror = true;
		this.setRotation(this.leftleg2, 0.9773844F, 0.0F, 0.0F);
		this.body2 = new ModelRenderer(this, 400, 50);
		this.body2.addBox(0.0F, -3.0F, -3.0F, 26, 14, 16);
		this.body2.setRotationPoint(-13.0F, -9.0F, 10.0F);
		this.body2.setTextureSize(512, 256);
		this.body2.mirror = true;
		this.setRotation(this.body2, -0.1047198F, 0.0F, 0.0F);
		this.leftleg3 = new ModelRenderer(this, 300, 51);
		this.leftleg3.addBox(-1.0F, -1.0F, -2.0F, 3, 19, 4);
		this.leftleg3.setRotationPoint(14.0F, 7.0F, 22.0F);
		this.leftleg3.setTextureSize(512, 256);
		this.leftleg3.mirror = true;
		this.setRotation(this.leftleg3, (-(float)Math.PI / 6F), 0.0F, 0.0F);
		this.tail2 = new ModelRenderer(this, 400, 106);
		this.tail2.addBox(-5.0F, -4.0F, 0.0F, 10, 7, 10);
		this.tail2.setRotationPoint(0.0F, -2.0F, 33.0F);
		this.tail2.setTextureSize(512, 256);
		this.tail2.mirror = true;
		this.setRotation(this.tail2, -0.1396263F, 0.0F, 0.0F);
		this.tail3 = new ModelRenderer(this, 400, 126);
		this.tail3.addBox(-3.0F, -2.0F, 0.0F, 6, 5, 10);
		this.tail3.setRotationPoint(0.0F, -2.0F, 42.0F);
		this.tail3.setTextureSize(512, 256);
		this.tail3.mirror = true;
		this.setRotation(this.tail3, -0.1396263F, 0.0F, 0.0F);
		this.lclaw2 = new ModelRenderer(this, 300, 76);
		this.lclaw2.addBox(-3.0F, 0.0F, -3.0F, 7, 3, 8);
		this.lclaw2.setRotationPoint(14.0F, 21.0F, 13.0F);
		this.lclaw2.setTextureSize(512, 256);
		this.lclaw2.mirror = true;
		this.setRotation(this.lclaw2, 0.0F, 0.0F, 0.0F);
		this.lclaw4 = new ModelRenderer(this, 310, 123);
		this.lclaw4.addBox(0.0F, 1.0F, -7.0F, 1, 2, 4);
		this.lclaw4.setRotationPoint(14.0F, 21.0F, 13.0F);
		this.lclaw4.setTextureSize(512, 256);
		this.lclaw4.mirror = true;
		this.setRotation(this.lclaw4, 0.0F, 0.0F, 0.0F);
		this.lclaw5 = new ModelRenderer(this, 297, 123);
		this.lclaw5.addBox(-2.5F, 1.0F, -7.0F, 1, 2, 4);
		this.lclaw5.setRotationPoint(14.0F, 21.0F, 13.0F);
		this.lclaw5.setTextureSize(512, 256);
		this.lclaw5.mirror = true;
		this.setRotation(this.lclaw5, 0.0F, 0.0F, 0.0F);
		this.lclaw6 = new ModelRenderer(this, 322, 123);
		this.lclaw6.addBox(2.5F, 1.0F, -7.0F, 1, 2, 4);
		this.lclaw6.setRotationPoint(14.0F, 21.0F, 13.0F);
		this.lclaw6.setTextureSize(512, 256);
		this.lclaw6.mirror = true;
		this.setRotation(this.lclaw6, 0.0F, 0.0F, 0.0F);
		this.lclaw7 = new ModelRenderer(this, 334, 123);
		this.lclaw7.addBox(0.0F, 1.0F, 5.0F, 1, 2, 3);
		this.lclaw7.setRotationPoint(14.0F, 21.0F, 13.0F);
		this.lclaw7.setTextureSize(512, 256);
		this.lclaw7.mirror = true;
		this.setRotation(this.lclaw7, 0.0F, 0.0F, 0.0F);
		this.tail4 = new ModelRenderer(this, 400, 143);
		this.tail4.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 10);
		this.tail4.setRotationPoint(0.0F, 0.0F, 51.0F);
		this.tail4.setTextureSize(512, 256);
		this.tail4.mirror = true;
		this.setRotation(this.tail4, -0.1396263F, 0.0F, 0.0F);
		this.tail5 = new ModelRenderer(this, 400, 159);
		this.tail5.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 10);
		this.tail5.setRotationPoint(0.0F, 2.0F, 59.0F);
		this.tail5.setTextureSize(512, 256);
		this.tail5.mirror = true;
		this.setRotation(this.tail5, -0.1396263F, 0.0F, 0.0F);
		this.neck1 = new ModelRenderer(this, 400, 7);
		this.neck1.addBox(-12.0F, -4.0F, 0.0F, 24, 8, 8);
		this.neck1.setRotationPoint(0.0F, -7.0F, -5.0F);
		this.neck1.setTextureSize(512, 256);
		this.neck1.mirror = true;
		this.setRotation(this.neck1, 0.0872665F, 0.0F, 0.0F);
		this.neck3 = new ModelRenderer(this, 365, 5);
		this.neck3.addBox(-3.0F, -3.0F, -9.0F, 6, 6, 10);
		this.neck3.setRotationPoint(0.0F, -8.0F, -5.0F);
		this.neck3.setTextureSize(512, 256);
		this.neck3.mirror = true;
		this.setRotation(this.neck3, 0.0174533F, 0.0F, 0.0F);
		this.head3 = new ModelRenderer(this, 143, 149);
		this.head3.addBox(-2.0F, -3.0F, -15.0F, 4, 4, 17);
		this.head3.setRotationPoint(0.0F, -6.0F, -34.0F);
		this.head3.setTextureSize(512, 256);
		this.head3.mirror = true;
		this.setRotation(this.head3, -0.2443461F, 0.0F, 0.0F);
		this.jaw1 = new ModelRenderer(this, 143, 173);
		this.jaw1.addBox(-1.5F, 1.0F, -14.0F, 3, 2, 12);
		this.jaw1.setRotationPoint(0.0F, -6.0F, -34.0F);
		this.jaw1.setTextureSize(512, 256);
		this.jaw1.mirror = true;
		this.setRotation(this.jaw1, 0.1919862F, 0.0F, 0.0F);
		this.jaw5 = new ModelRenderer(this, 144, 206);
		this.jaw5.addBox(-2.5F, 1.0F, -3.0F, 5, 2, 6);
		this.jaw5.setRotationPoint(0.0F, -6.0F, -34.0F);
		this.jaw5.setTextureSize(512, 256);
		this.jaw5.mirror = true;
		this.setRotation(this.jaw5, 0.1919862F, 0.0F, 0.0F);
		this.head7 = new ModelRenderer(this, 144, 192);
		this.head7.addBox(-3.0F, -4.0F, -3.0F, 6, 5, 7);
		this.head7.setRotationPoint(0.0F, -6.0F, -34.0F);
		this.head7.setTextureSize(512, 256);
		this.head7.mirror = true;
		this.setRotation(this.head7, -0.2443461F, 0.0F, 0.0F);
		this.rightleg1 = new ModelRenderer(this, 250, 10);
		this.rightleg1.addBox(-1.0F, -1.0F, -3.0F, 5, 9, 9);
		this.rightleg1.setRotationPoint(-17.0F, -8.0F, 13.0F);
		this.rightleg1.setTextureSize(512, 256);
		this.rightleg1.mirror = true;
		this.setRotation(this.rightleg1, -0.5934119F, 0.0F, 0.0F);
		this.rightleg2 = new ModelRenderer(this, 250, 32);
		this.rightleg2.addBox(0.0F, 6.0F, -7.0F, 4, 12, 5);
		this.rightleg2.setRotationPoint(-17.0F, -8.0F, 13.0F);
		this.rightleg2.setTextureSize(512, 256);
		this.rightleg2.mirror = true;
		this.setRotation(this.rightleg2, 0.9773844F, 0.0F, 0.0F);
		this.rightleg3 = new ModelRenderer(this, 250, 52);
		this.rightleg3.addBox(1.0F, 1.0F, -2.0F, 3, 19, 4);
		this.rightleg3.setRotationPoint(-17.0F, 5.0F, 23.0F);
		this.rightleg3.setTextureSize(512, 256);
		this.rightleg3.mirror = true;
		this.setRotation(this.rightleg3, (-(float)Math.PI / 6F), 0.0F, 0.0F);
		this.rclaw2 = new ModelRenderer(this, 250, 76);
		this.rclaw2.addBox(-1.0F, 0.0F, -3.0F, 7, 3, 8);
		this.rclaw2.setRotationPoint(-17.0F, 21.0F, 13.0F);
		this.rclaw2.setTextureSize(512, 256);
		this.rclaw2.mirror = true;
		this.setRotation(this.rclaw2, 0.0F, 0.0F, 0.0F);
		this.rclaw4 = new ModelRenderer(this, 247, 123);
		this.rclaw4.addBox(2.0F, 1.0F, -7.0F, 1, 2, 4);
		this.rclaw4.setRotationPoint(-17.0F, 21.0F, 13.0F);
		this.rclaw4.setTextureSize(512, 256);
		this.rclaw4.mirror = true;
		this.setRotation(this.rclaw4, 0.0F, 0.0F, 0.0F);
		this.rclaw5 = new ModelRenderer(this, 258, 123);
		this.rclaw5.addBox(-0.5F, 1.0F, -7.0F, 1, 2, 4);
		this.rclaw5.setRotationPoint(-17.0F, 21.0F, 13.0F);
		this.rclaw5.setTextureSize(512, 256);
		this.rclaw5.mirror = true;
		this.setRotation(this.rclaw5, 0.0F, 0.0F, 0.0F);
		this.rclaw7 = new ModelRenderer(this, 283, 123);
		this.rclaw7.addBox(2.0F, 1.0F, 5.0F, 1, 2, 3);
		this.rclaw7.setRotationPoint(-17.0F, 21.0F, 13.0F);
		this.rclaw7.setTextureSize(512, 256);
		this.rclaw7.mirror = true;
		this.setRotation(this.rclaw7, 0.0F, 0.0F, 0.0F);
		this.rclaw6 = new ModelRenderer(this, 270, 123);
		this.rclaw6.addBox(4.5F, 1.0F, -7.0F, 1, 2, 4);
		this.rclaw6.setRotationPoint(-17.0F, 21.0F, 13.0F);
		this.rclaw6.setTextureSize(512, 256);
		this.rclaw6.mirror = true;
		this.setRotation(this.rclaw6, 0.0F, 0.0F, 0.0F);
		this.wing1 = new ModelRenderer(this, 10, 30);
		this.wing1.addBox(-1.0F, -1.0F, -1.0F, 23, 2, 2);
		this.wing1.setRotationPoint(13.0F, -12.0F, 3.0F);
		this.wing1.setTextureSize(512, 256);
		this.wing1.mirror = true;
		this.setRotation(this.wing1, 0.0F, 0.0872665F, 0.0F);
		this.wing2 = new ModelRenderer(this, 10, 40);
		this.wing2.addBox(-1.0F, -1.0F, -1.0F, 44, 2, 2);
		this.wing2.setRotationPoint(34.0F, -12.0F, 1.0F);
		this.wing2.setTextureSize(512, 256);
		this.wing2.mirror = true;
		this.setRotation(this.wing2, 0.0F, 0.0F, 0.0F);
		this.mem1 = new ModelRenderer(this, 10, 60);
		this.mem1.addBox(-2.0F, 0.0F, 0.0F, 24, 1, 21);
		this.mem1.setRotationPoint(13.0F, -12.0F, 3.0F);
		this.mem1.setTextureSize(512, 256);
		this.mem1.mirror = true;
		this.setRotation(this.mem1, 0.0F, 0.0872665F, 0.0F);
		this.mem2 = new ModelRenderer(this, 10, 85);
		this.mem2.addBox(0.0F, 0.0F, 0.0F, 43, 1, 21);
		this.mem2.setRotationPoint(34.0F, -12.0F, 1.0F);
		this.mem2.setTextureSize(512, 256);
		this.mem2.mirror = true;
		this.setRotation(this.mem2, 0.0F, 0.0F, 0.0F);
		this.lshoulder = new ModelRenderer(this, 370, 78);
		this.lshoulder.addBox(0.0F, 0.0F, 0.0F, 5, 2, 8);
		this.lshoulder.setRotationPoint(8.0F, -13.0F, 1.0F);
		this.lshoulder.setTextureSize(512, 256);
		this.lshoulder.mirror = true;
		this.setRotation(this.lshoulder, 0.0698132F, 0.0F, 0.0F);
		this.rshoulder = new ModelRenderer(this, 370, 66);
		this.rshoulder.addBox(0.0F, 0.0F, 0.0F, 5, 2, 8);
		this.rshoulder.setRotationPoint(-13.0F, -13.0F, 1.0F);
		this.rshoulder.setTextureSize(512, 256);
		this.rshoulder.mirror = true;
		this.setRotation(this.rshoulder, 0.0698132F, 0.0F, 0.0F);
		this.rwing1 = new ModelRenderer(this, 10, 140);
		this.rwing1.addBox(-22.0F, -1.0F, -1.0F, 23, 2, 2);
		this.rwing1.setRotationPoint(-13.0F, -12.0F, 3.0F);
		this.rwing1.setTextureSize(512, 256);
		this.rwing1.mirror = true;
		this.setRotation(this.rwing1, 0.0F, -0.0872665F, 0.0F);
		this.rmem1 = new ModelRenderer(this, 10, 170);
		this.rmem1.addBox(-22.0F, 0.0F, 0.0F, 24, 1, 21);
		this.rmem1.setRotationPoint(-13.0F, -12.0F, 3.0F);
		this.rmem1.setTextureSize(512, 256);
		this.rmem1.mirror = true;
		this.setRotation(this.rmem1, 0.0F, -0.0872665F, 0.0F);
		this.rwing2 = new ModelRenderer(this, 10, 150);
		this.rwing2.addBox(-43.0F, -1.0F, -1.0F, 44, 2, 2);
		this.rwing2.setRotationPoint(-34.0F, -12.0F, 1.0F);
		this.rwing2.setTextureSize(512, 256);
		this.rwing2.mirror = true;
		this.setRotation(this.rwing2, 0.0F, 0.0F, 0.0F);
		this.rmem2 = new ModelRenderer(this, 10, 195);
		this.rmem2.addBox(-43.0F, 0.0F, 0.0F, 43, 1, 21);
		this.rmem2.setRotationPoint(-34.0F, -12.0F, 1.0F);
		this.rmem2.setTextureSize(512, 256);
		this.rmem2.mirror = true;
		this.setRotation(this.rmem2, 0.0F, 0.0F, 0.0F);
		this.neck4 = new ModelRenderer(this, 366, 23);
		this.neck4.addBox(-2.5F, -2.5F, -9.0F, 5, 5, 10);
		this.neck4.setRotationPoint(0.0F, -8.0F, -14.0F);
		this.neck4.setTextureSize(512, 256);
		this.neck4.mirror = true;
		this.setRotation(this.neck4, 0.1396263F, 0.0F, 0.0F);
		this.neck5 = new ModelRenderer(this, 369, 41);
		this.neck5.addBox(-2.0F, -2.0F, -9.0F, 4, 4, 10);
		this.neck5.setRotationPoint(0.0F, -7.0F, -22.0F);
		this.neck5.setTextureSize(512, 256);
		this.neck5.mirror = true;
		this.setRotation(this.neck5, 0.1396263F, 0.0F, 0.0F);
		this.wing3 = new ModelRenderer(this, 10, 46);
		this.wing3.addBox(0.0F, 0.0F, 0.0F, 44, 2, 2);
		this.wing3.setRotationPoint(13.0F, -13.0F, 3.0F);
		this.wing3.setTextureSize(512, 256);
		this.wing3.mirror = true;
		this.setRotation(this.wing3, 0.0F, 0.0F, -0.3490659F);
		this.mem3 = new ModelRenderer(this, 10, 110);
		this.mem3.addBox(0.0F, 0.0F, 0.0F, 43, 1, 21);
		this.mem3.setRotationPoint(13.0F, -12.5F, 5.0F);
		this.mem3.setTextureSize(512, 256);
		this.mem3.mirror = true;
		this.setRotation(this.mem3, 0.0F, 0.0F, -0.3490659F);
		this.rwing3 = new ModelRenderer(this, 10, 156);
		this.rwing3.addBox(-43.0F, 0.0F, 0.0F, 44, 2, 2);
		this.rwing3.setRotationPoint(-13.0F, -13.0F, 3.0F);
		this.rwing3.setTextureSize(512, 256);
		this.rwing3.mirror = true;
		this.setRotation(this.rwing3, 0.0F, 0.0F, 0.3490659F);
		this.rmem3 = new ModelRenderer(this, 10, 221);
		this.rmem3.addBox(-42.0F, 0.0F, 0.0F, 43, 1, 21);
		this.rmem3.setRotationPoint(-13.0F, -12.5F, 5.0F);
		this.rmem3.setTextureSize(512, 256);
		this.rmem3.mirror = true;
		this.setRotation(this.rmem3, 0.0F, 0.0F, 0.3490659F);
		this.wing4 = new ModelRenderer(this, 10, 46);
		this.wing4.addBox(0.0F, 0.0F, 0.0F, 44, 2, 2);
		this.wing4.setRotationPoint(13.0F, -12.0F, 3.0F);
		this.wing4.setTextureSize(512, 256);
		this.wing4.mirror = true;
		this.setRotation(this.wing4, 0.0F, 0.0F, 0.3490659F);
		this.mem4 = new ModelRenderer(this, 10, 110);
		this.mem4.addBox(0.0F, 0.0F, 0.0F, 43, 1, 21);
		this.mem4.setRotationPoint(13.0F, -11.5F, 5.0F);
		this.mem4.setTextureSize(512, 256);
		this.mem4.mirror = true;
		this.setRotation(this.mem4, 0.0F, 0.0F, 0.3490659F);
		this.rwing4 = new ModelRenderer(this, 10, 156);
		this.rwing4.addBox(-43.0F, 0.0F, 0.0F, 44, 2, 2);
		this.rwing4.setRotationPoint(-13.0F, -12.0F, 3.0F);
		this.rwing4.setTextureSize(512, 256);
		this.rwing4.mirror = true;
		this.setRotation(this.rwing4, 0.0F, 0.0F, -0.3490659F);
		this.rmem4 = new ModelRenderer(this, 10, 221);
		this.rmem4.addBox(-42.0F, 0.0F, 0.0F, 43, 1, 21);
		this.rmem4.setRotationPoint(-13.0F, -11.5F, 5.0F);
		this.rmem4.setTextureSize(512, 256);
		this.rmem4.mirror = true;
		this.setRotation(this.rmem4, 0.0F, 0.0F, -0.3490659F);
		this.Tailspike1 = new ModelRenderer(this, 150, 0);
		this.Tailspike1.addBox(-7.0F, 0.0F, 0.0F, 14, 2, 6);
		this.Tailspike1.setRotationPoint(0.0F, 2.0F, 69.0F);
		this.Tailspike1.setTextureSize(512, 256);
		this.Tailspike1.mirror = true;
		this.setRotation(this.Tailspike1, 0.0F, 0.0F, 0.0F);
		this.Tailspike2 = new ModelRenderer(this, 150, 11);
		this.Tailspike2.addBox(-5.0F, 0.0F, 0.0F, 10, 2, 6);
		this.Tailspike2.setRotationPoint(0.0F, 2.0F, 75.0F);
		this.Tailspike2.setTextureSize(512, 256);
		this.Tailspike2.mirror = true;
		this.setRotation(this.Tailspike2, 0.0F, 0.0F, 0.0F);
		this.Tailspike3 = new ModelRenderer(this, 150, 23);
		this.Tailspike3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 15);
		this.Tailspike3.setRotationPoint(0.0F, 2.0F, 80.0F);
		this.Tailspike3.setTextureSize(512, 256);
		this.Tailspike3.mirror = true;
		this.setRotation(this.Tailspike3, 0.0F, 0.0F, 0.0F);
		this.headfin = new ModelRenderer(this, 150, 216);
		this.headfin.addBox(-0.5F, -3.0F, 3.0F, 1, 4, 4);
		this.headfin.setRotationPoint(0.0F, -6.0F, -34.0F);
		this.headfin.setTextureSize(512, 256);
		this.headfin.mirror = true;
		this.setRotation(this.headfin, 0.0872665F, 0.0F, 0.0F);
		this.backfin1 = new ModelRenderer(this, 69, 0);
		this.backfin1.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 6);
		this.backfin1.setRotationPoint(0.0F, -11.0F, 0.0F);
		this.backfin1.setTextureSize(512, 256);
		this.backfin1.mirror = true;
		this.setRotation(this.backfin1, ((float)Math.PI / 4F), 0.0F, 0.0F);
		this.backfin2 = new ModelRenderer(this, 85, 0);
		this.backfin2.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 4);
		this.backfin2.setRotationPoint(0.0F, -11.0F, 10.0F);
		this.backfin2.setTextureSize(512, 256);
		this.backfin2.mirror = true;
		this.setRotation(this.backfin2, ((float)Math.PI / 4F), 0.0F, 0.0F);
		this.neck3L = new ModelRenderer(this, 365, 100);
		this.neck3L.addBox(-3.0F, -3.0F, -9.0F, 6, 6, 10);
		this.neck3L.setRotationPoint(8.0F, -8.0F, -5.0F);
		this.neck3L.setTextureSize(512, 256);
		this.neck3L.mirror = true;
		this.setRotation(this.neck3L, 0.0174533F, 0.0F, 0.0F);
		this.neck4L = new ModelRenderer(this, 366, 119);
		this.neck4L.addBox(-2.5F, -2.5F, -9.0F, 5, 5, 10);
		this.neck4L.setRotationPoint(8.0F, -8.0F, -14.0F);
		this.neck4L.setTextureSize(512, 256);
		this.neck4L.mirror = true;
		this.setRotation(this.neck4L, 0.1396263F, 0.0F, 0.0F);
		this.neck3R = new ModelRenderer(this, 365, 175);
		this.neck3R.addBox(-3.0F, -3.0F, -9.0F, 6, 6, 10);
		this.neck3R.setRotationPoint(-8.0F, -8.0F, -5.0F);
		this.neck3R.setTextureSize(512, 256);
		this.neck3R.mirror = true;
		this.setRotation(this.neck3R, 0.0174533F, 0.0F, 0.0F);
		this.neck4R = new ModelRenderer(this, 366, 194);
		this.neck4R.addBox(-2.5F, -2.5F, -9.0F, 5, 5, 10);
		this.neck4R.setRotationPoint(-8.0F, -8.0F, -14.0F);
		this.neck4R.setTextureSize(512, 256);
		this.neck4R.mirror = true;
		this.setRotation(this.neck4R, 0.1396263F, 0.0F, 0.0F);
		this.neck5L = new ModelRenderer(this, 369, 137);
		this.neck5L.addBox(-2.0F, -2.0F, -9.0F, 4, 4, 10);
		this.neck5L.setRotationPoint(8.0F, -7.0F, -23.0F);
		this.neck5L.setTextureSize(512, 256);
		this.neck5L.mirror = true;
		this.setRotation(this.neck5L, 0.1396263F, 0.0F, 0.0F);
		this.neck5R = new ModelRenderer(this, 369, 212);
		this.neck5R.addBox(-2.0F, -2.0F, -9.0F, 4, 4, 10);
		this.neck5R.setRotationPoint(-8.0F, -7.0F, -23.0F);
		this.neck5R.setTextureSize(512, 256);
		this.neck5R.mirror = true;
		this.setRotation(this.neck5R, 0.1396263F, 0.0F, 0.0F);
		this.jaw5L = new ModelRenderer(this, 200, 206);
		this.jaw5L.addBox(-2.5F, 1.0F, -3.0F, 5, 2, 6);
		this.jaw5L.setRotationPoint(8.0F, -6.0F, -34.0F);
		this.jaw5L.setTextureSize(512, 256);
		this.jaw5L.mirror = true;
		this.setRotation(this.jaw5L, 0.1919862F, 0.0F, 0.0F);
		this.jaw5R = new ModelRenderer(this, 250, 206);
		this.jaw5R.addBox(-2.5F, 1.0F, -3.0F, 5, 2, 6);
		this.jaw5R.setRotationPoint(-8.0F, -6.0F, -34.0F);
		this.jaw5R.setTextureSize(512, 256);
		this.jaw5R.mirror = true;
		this.setRotation(this.jaw5R, 0.1919862F, 0.0F, 0.0F);
		this.head7L = new ModelRenderer(this, 200, 192);
		this.head7L.addBox(-3.0F, -4.0F, -3.0F, 6, 5, 7);
		this.head7L.setRotationPoint(8.0F, -6.0F, -34.0F);
		this.head7L.setTextureSize(512, 256);
		this.head7L.mirror = true;
		this.setRotation(this.head7L, -0.2443461F, 0.0F, 0.0F);
		this.headfinL = new ModelRenderer(this, 200, 216);
		this.headfinL.addBox(-0.5F, -3.0F, 3.0F, 1, 4, 4);
		this.headfinL.setRotationPoint(8.0F, -6.0F, -34.0F);
		this.headfinL.setTextureSize(512, 256);
		this.headfinL.mirror = true;
		this.setRotation(this.headfinL, 0.0872665F, 0.0F, 0.0F);
		this.headfinR = new ModelRenderer(this, 250, 216);
		this.headfinR.addBox(-0.5F, -3.0F, 3.0F, 1, 4, 4);
		this.headfinR.setRotationPoint(-8.0F, -6.0F, -34.0F);
		this.headfinR.setTextureSize(512, 256);
		this.headfinR.mirror = true;
		this.setRotation(this.headfinR, 0.0872665F, 0.0F, 0.0F);
		this.head7R = new ModelRenderer(this, 250, 192);
		this.head7R.addBox(-3.0F, -4.0F, -3.0F, 6, 5, 7);
		this.head7R.setRotationPoint(-8.0F, -6.0F, -34.0F);
		this.head7R.setTextureSize(512, 256);
		this.head7R.mirror = true;
		this.setRotation(this.head7R, -0.2443461F, 0.0F, 0.0F);
		this.jaw1L = new ModelRenderer(this, 200, 173);
		this.jaw1L.addBox(-1.5F, 1.0F, -14.0F, 3, 2, 12);
		this.jaw1L.setRotationPoint(8.0F, -6.0F, -34.0F);
		this.jaw1L.setTextureSize(512, 256);
		this.jaw1L.mirror = true;
		this.setRotation(this.jaw1L, 0.1919862F, 0.0F, 0.0F);
		this.jaw1R = new ModelRenderer(this, 250, 173);
		this.jaw1R.addBox(-1.5F, 1.0F, -14.0F, 3, 2, 12);
		this.jaw1R.setRotationPoint(-8.0F, -6.0F, -34.0F);
		this.jaw1R.setTextureSize(512, 256);
		this.jaw1R.mirror = true;
		this.setRotation(this.jaw1R, 0.1919862F, 0.0F, 0.0F);
		this.head3L = new ModelRenderer(this, 200, 149);
		this.head3L.addBox(-2.0F, -3.0F, -15.0F, 4, 4, 17);
		this.head3L.setRotationPoint(8.0F, -6.0F, -34.0F);
		this.head3L.setTextureSize(512, 256);
		this.head3L.mirror = true;
		this.setRotation(this.head3L, -0.2443461F, 0.0F, 0.0F);
		this.head3R = new ModelRenderer(this, 250, 149);
		this.head3R.addBox(-2.0F, -3.0F, -15.0F, 4, 4, 17);
		this.head3R.setRotationPoint(-8.0F, -6.0F, -34.0F);
		this.head3R.setTextureSize(512, 256);
		this.head3R.mirror = true;
		this.setRotation(this.head3R, -0.2443461F, 0.0F, 0.0F);
		
	}

	
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		ThePrinceTeen c = (ThePrinceTeen)entity;
		RenderInfo r = null;
		float hf = 0.0F;
		float newangle = 0.0F;
		float newangle2 = 0.0F;
		float rnewangle = 0.0F;
		float rnewangle2 = 0.0F;
		float clawangle = 0.0F;
		float tailspeed = 0.26F;
		float tailamp = 0.08F;
		float pi4 = ((float)Math.PI / 4F);
		float h1, h2, h3;
		float d1, d2, d3;
		float Ljx, Rjx, jx;
		int current_activity = c.getActivity();
		
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		
		r = c.getRenderInfo();
		
		
		
		if ((double)f1 > 0.1 && current_activity == 0) {
			newangle = MathHelper.cos(f2 * 1.3F * this.wingspeed) * (float)Math.PI * 0.2F * f1;
		} else {
			newangle = MathHelper.cos(f2 * 0.3F * this.wingspeed) * (float)Math.PI * 0.04F;
		}
		if (current_activity == 1) {
			newangle = MathHelper.cos(f2 * 1.4F * this.wingspeed) * (float)Math.PI * 0.4F;
		}
		if (c.getAttacking() != 0) {
			newangle = MathHelper.cos(f2 * 1.7F * this.wingspeed) * (float)Math.PI * 0.4F;
		}

		this.wing1.rotateAngleZ = newangle - 0.4F;
		this.wing2.rotateAngleZ = newangle * 1.25F - 0.4F;
		this.wing3.rotateAngleZ = newangle - 0.6F;
		this.wing4.rotateAngleZ = newangle - 0.2F;
		this.wing2.rotationPointY = this.wing1.rotationPointY + (float)Math.sin((double)this.wing1.rotateAngleZ) * 22.0F;
		this.wing2.rotationPointX = this.wing1.rotationPointX + (float)Math.cos((double)this.wing1.rotateAngleZ) * 22.0F;
		
		this.mem1.rotateAngleZ = this.wing1.rotateAngleZ;
		this.mem2.rotateAngleZ = this.wing2.rotateAngleZ;
		this.mem3.rotateAngleZ = this.wing3.rotateAngleZ;
		this.mem4.rotateAngleZ = this.wing4.rotateAngleZ;
		this.mem2.rotationPointY = this.wing2.rotationPointY;
		this.mem2.rotationPointX = this.wing2.rotationPointX;
		
		this.rwing1.rotateAngleZ = -newangle + 0.4F;
		this.rwing2.rotateAngleZ = -newangle * 1.25F + 0.4F;
		this.rwing3.rotateAngleZ = -newangle + 0.6F;
		this.rwing4.rotateAngleZ = -newangle + 0.2F;
		this.rwing2.rotationPointY = this.rwing1.rotationPointY - (float)Math.sin((double)this.rwing1.rotateAngleZ) * 22.0F;
		this.rwing2.rotationPointX = this.rwing1.rotationPointX - (float)Math.cos((double)this.rwing1.rotateAngleZ) * 22.0F;
		
		this.rmem1.rotateAngleZ = this.rwing1.rotateAngleZ;
		this.rmem2.rotateAngleZ = this.rwing2.rotateAngleZ;
		this.rmem3.rotateAngleZ = this.rwing3.rotateAngleZ;
		this.rmem4.rotateAngleZ = this.rwing4.rotateAngleZ;
		this.rmem2.rotationPointY = this.rwing2.rotationPointY;
		this.rmem2.rotationPointX = this.rwing2.rotationPointX;
		
		
		
		if ((double)f1 > 0.1) {
			newangle = MathHelper.cos(f2 * 0.55F * this.wingspeed) * (float)Math.PI * 0.25F * f1;
			newangle2 = MathHelper.cos((float)((double)(f2 * 0.55F * this.wingspeed) + (Math.PI / 2D))) * (float)Math.PI * 0.25F * f1;
			rnewangle = newangle;
			rnewangle2 = newangle2;
			clawangle = 0.0F;
		} else {
			newangle = 0.0F;
			newangle2 = 0.0F;
			rnewangle = 0.0F;
			rnewangle2 = 0.0F;
			clawangle = 0.0F;
		}

		if (c.getAttacking() != 0) {
			newangle = MathHelper.cos(f2 * this.wingspeed) * (float)Math.PI * 0.25F;
			newangle2 = MathHelper.cos((float)((double)(f2 * this.wingspeed) + (Math.PI / 2D))) * (float)Math.PI * 0.25F;
			rnewangle = newangle;
			rnewangle2 = newangle2;
			clawangle = 0.0F;
		}
		
		if (current_activity == 1 && c.getAttacking() == 0)
		{
			newangle = -0.5F;
			newangle2 = -1.25F;
			rnewangle = 0.5F;
			rnewangle2 = 1.25F;
		}

		if (current_activity == 1) {
			clawangle = -0.685F;
		}

		this.leftleg1.rotateAngleX = newangle - 0.575F;
		this.leftleg2.rotateAngleX = newangle + 0.977F;
		this.leftleg3.rotateAngleX = newangle2 - 0.523F;
		this.leftleg3.rotationPointY = this.leftleg2.rotationPointY + (float)Math.cos((double)this.leftleg2.rotateAngleX) * 14.0F + 6.0F;
		this.leftleg3.rotationPointZ = this.leftleg2.rotationPointZ + (float)Math.sin((double)this.leftleg2.rotateAngleX) * 14.0F;
		
		this.lclaw2.rotationPointY = this.leftleg3.rotationPointY + (float)Math.cos((double)this.leftleg3.rotateAngleX) * 17.0F;
		this.lclaw2.rotationPointZ = this.leftleg3.rotationPointZ + (float)Math.sin((double)this.leftleg3.rotateAngleX) * 17.0F - 1.0F;
		
		this.lclaw4.rotationPointY = this.lclaw2.rotationPointY;
		this.lclaw4.rotationPointZ = this.lclaw2.rotationPointZ;
		this.lclaw5.rotationPointY = this.lclaw2.rotationPointY;
		this.lclaw5.rotationPointZ = this.lclaw2.rotationPointZ;
		this.lclaw6.rotationPointY = this.lclaw2.rotationPointY;
		this.lclaw6.rotationPointZ = this.lclaw2.rotationPointZ;
		this.lclaw7.rotationPointY = this.lclaw2.rotationPointY;
		this.lclaw7.rotationPointZ = this.lclaw2.rotationPointZ;
		
		this.lclaw2.rotateAngleX = clawangle;
		this.lclaw4.rotateAngleX = clawangle;
		this.lclaw5.rotateAngleX = clawangle;
		this.lclaw6.rotateAngleX = clawangle;
		this.lclaw7.rotateAngleX = clawangle;
		
		
		this.rightleg1.rotateAngleX = -rnewangle - 0.575F;
		this.rightleg2.rotateAngleX = -rnewangle + 0.977F;
		this.rightleg3.rotateAngleX = -rnewangle2 - 0.523F;
		this.rightleg3.rotationPointY = this.rightleg2.rotationPointY + (float)Math.cos((double)this.rightleg2.rotateAngleX) * 14.0F + 5.0F;
		this.rightleg3.rotationPointZ = this.rightleg2.rotationPointZ + (float)Math.sin((double)this.rightleg2.rotateAngleX) * 14.0F;
		
		this.rclaw2.rotationPointY = this.rightleg3.rotationPointY + (float)Math.cos((double)this.rightleg3.rotateAngleX) * 17.0F;
		this.rclaw2.rotationPointZ = this.rightleg3.rotationPointZ + (float)Math.sin((double)this.rightleg3.rotateAngleX) * 17.0F - 1.0F;
		
		this.rclaw4.rotationPointY = this.rclaw2.rotationPointY;
		this.rclaw4.rotationPointZ = this.rclaw2.rotationPointZ;
		this.rclaw5.rotationPointY = this.rclaw2.rotationPointY;
		this.rclaw5.rotationPointZ = this.rclaw2.rotationPointZ;
		this.rclaw6.rotationPointY = this.rclaw2.rotationPointY;
		this.rclaw6.rotationPointZ = this.rclaw2.rotationPointZ;
		this.rclaw7.rotationPointY = this.rclaw2.rotationPointY;
		this.rclaw7.rotationPointZ = this.rclaw2.rotationPointZ;
		
		this.rclaw2.rotateAngleX = clawangle;
		this.rclaw4.rotateAngleX = clawangle;
		this.rclaw5.rotateAngleX = clawangle;
		this.rclaw6.rotateAngleX = clawangle;
		this.rclaw7.rotateAngleX = clawangle;
		
		
		
		if (c.getAttacking() != 0) {
			tailspeed = 0.56F;
			tailamp = 0.19F;
		}
		if (c.isSitting()) {
			tailamp = 0.0F;
		}
		this.tail1.rotateAngleY = MathHelper.cos(f2 * tailspeed * this.wingspeed) * (float)Math.PI * tailamp / 4.0F;
		this.tail2.rotationPointZ = this.tail1.rotationPointZ + (float)Math.cos((double)this.tail1.rotateAngleY) * 11.0F;
		this.tail2.rotationPointX = this.tail1.rotationPointX + (float)Math.sin((double)this.tail1.rotateAngleY) * 11.0F;
		this.tail2.rotateAngleY = MathHelper.cos(f2 * tailspeed * this.wingspeed - pi4) * (float)Math.PI * tailamp;
		this.tail3.rotationPointZ = this.tail2.rotationPointZ + (float)Math.cos((double)this.tail2.rotateAngleY) * 9.0F;
		this.tail3.rotationPointX = this.tail2.rotationPointX + (float)Math.sin((double)this.tail2.rotateAngleY) * 9.0F;
		this.tail3.rotateAngleY = MathHelper.cos(f2 * tailspeed * this.wingspeed - 2.0F * pi4) * (float)Math.PI * tailamp;
		this.tail4.rotationPointZ = this.tail3.rotationPointZ + (float)Math.cos((double)this.tail3.rotateAngleY) * 9.0F;
		this.tail4.rotationPointX = this.tail3.rotationPointX + (float)Math.sin((double)this.tail3.rotateAngleY) * 9.0F;
		this.tail4.rotateAngleY = MathHelper.cos(f2 * tailspeed * this.wingspeed - 3.0F * pi4) * (float)Math.PI * tailamp;
		newangle = MathHelper.cos(f2 * tailspeed * this.wingspeed - 3.0F * pi4) * (float)Math.PI * tailamp;
		newangle /= 2.0F;
		this.tail5.rotationPointZ = this.tail4.rotationPointZ + (float)Math.cos((double)this.tail4.rotateAngleY) * 9.0F;
		this.tail5.rotationPointX = this.tail4.rotationPointX + (float)Math.sin((double)this.tail4.rotateAngleY) * 9.0F;
		this.tail5.rotateAngleY = this.tail4.rotateAngleY + newangle;
		this.Tailspike1.rotationPointZ = this.tail5.rotationPointZ + (float)Math.cos((double)this.tail5.rotateAngleY) * 9.0F;
		this.Tailspike1.rotationPointX = this.tail5.rotationPointX + (float)Math.sin((double)this.tail5.rotateAngleY) * 9.0F;
		this.Tailspike2.rotationPointZ = this.tail5.rotationPointZ + (float)Math.cos((double)this.tail5.rotateAngleY) * 15.0F;
		this.Tailspike2.rotationPointX = this.tail5.rotationPointX + (float)Math.sin((double)this.tail5.rotateAngleY) * 15.0F;
		this.Tailspike1.rotateAngleY = this.Tailspike2.rotateAngleY = this.tail5.rotateAngleY + newangle * 2.0F / 3.0F;
		this.Tailspike3.rotationPointZ = this.Tailspike1.rotationPointZ + (float)Math.cos((double)this.Tailspike1.rotateAngleY) * 11.0F;
		this.Tailspike3.rotationPointX = this.Tailspike1.rotationPointX + (float)Math.sin((double)this.Tailspike1.rotateAngleY) * 11.0F;
		this.Tailspike3.rotateAngleY = this.Tailspike1.rotateAngleY + newangle * 3.0F / 2.0F;
		
		
		
		if (c.getActivity() == 1) {
			
			
			f3 = (c.prevRotationYaw - c.rotationYaw) * 10.0F;
			f3 = -f3;
			r.rf1 += (f3 - r.rf1) / 50.0F;
			if (r.rf1 > 50.0F) r.rf1 = 50.0F;
			if (r.rf1 < -50.0F) r.rf1 = -50.0F;
			f3 = r.rf1;
		}

		
		h1 = h2 = h3 = f3 * 2.0F / 3.0F;
		d1 = d2 = d3 = f4 * 2.0F / 3.0F;
		if (h1 < 0.0F) {
			h2 = h3 = h1 / 2.0F;
			d2 = d3 = d1 / 2.0F;
		} else {
			h2 = h1 = h3 / 2.0F;
			d2 = d1 = d3 / 2.0F;
		}
		this.head7.rotateAngleY = (float)Math.toRadians((double)h2);
		this.head3.rotateAngleY = (float)Math.toRadians((double)h2);
		this.headfin.rotateAngleY = (float)Math.toRadians((double)h2);
		this.jaw5.rotateAngleY = (float)Math.toRadians((double)h2);
		this.jaw1.rotateAngleY = (float)Math.toRadians((double)h2);
		this.neck3.rotateAngleY = (float)Math.toRadians((double)h2) / 8.0F;
		this.neck4.rotateAngleY = (float)Math.toRadians((double)h2) / 4.0F;
		this.neck5.rotateAngleY = (float)Math.toRadians((double)h2) / 2.0F;
		
		this.head7L.rotateAngleY = (float)Math.toRadians((double)h1);
		this.head3L.rotateAngleY = (float)Math.toRadians((double)h1);
		this.headfinL.rotateAngleY = (float)Math.toRadians((double)h1);
		this.jaw5L.rotateAngleY = (float)Math.toRadians((double)h1);
		this.jaw1L.rotateAngleY = (float)Math.toRadians((double)h1);
		this.neck3L.rotateAngleY = (float)Math.toRadians((double)h1) / 8.0F;
		this.neck4L.rotateAngleY = (float)Math.toRadians((double)h1) / 4.0F;
		this.neck5L.rotateAngleY = (float)Math.toRadians((double)h1) / 2.0F;
		
		this.head7R.rotateAngleY = (float)Math.toRadians((double)h3);
		this.head3R.rotateAngleY = (float)Math.toRadians((double)h3);
		this.headfinR.rotateAngleY = (float)Math.toRadians((double)h3);
		this.jaw5R.rotateAngleY = (float)Math.toRadians((double)h3);
		this.jaw1R.rotateAngleY = (float)Math.toRadians((double)h3);
		this.neck3R.rotateAngleY = (float)Math.toRadians((double)h3) / 8.0F;
		this.neck4R.rotateAngleY = (float)Math.toRadians((double)h3) / 4.0F;
		this.neck5R.rotateAngleY = (float)Math.toRadians((double)h3) / 2.0F;
		
		
		Ljx = jx = Rjx = 0.0F;
		if (c.getAttacking() != 0) {
			newangle = MathHelper.cos(f2 * 0.9F * this.wingspeed) * (float)Math.PI * 0.1F;
			Ljx = 0.25F + newangle;
			newangle = MathHelper.cos(f2 * 1.1F * this.wingspeed) * (float)Math.PI * 0.1F;
			Rjx = 0.25F + newangle;
			newangle = MathHelper.cos(f2 * 1.3F * this.wingspeed) * (float)Math.PI * 0.1F;
			jx = 0.25F + newangle;
		} else {
			newangle = MathHelper.cos(f2 * 0.25F * this.wingspeed) * (float)Math.PI * 0.02F;
			Ljx = 0.1F + newangle;
			newangle = MathHelper.cos(f2 * 0.3F * this.wingspeed) * (float)Math.PI * 0.02F;
			Rjx = 0.1F + newangle;
			newangle = MathHelper.cos(f2 * 0.35F * this.wingspeed) * (float)Math.PI * 0.02F;
			jx = 0.1F + newangle;
		}

		this.head7.rotateAngleX = (float)Math.toRadians((double)d2);
		this.head3.rotateAngleX = (float)Math.toRadians((double)d2);
		this.headfin.rotateAngleX = (float)Math.toRadians((double)d2) + 0.5F;
		this.jaw5.rotateAngleX = (float)Math.toRadians((double)d2) + jx;
		this.jaw1.rotateAngleX = (float)Math.toRadians((double)d2) + jx;
		
		this.head7L.rotateAngleX = (float)Math.toRadians((double)d1);
		this.head3L.rotateAngleX = (float)Math.toRadians((double)d1);
		this.headfinL.rotateAngleX = (float)Math.toRadians((double)d1) + 0.5F;
		this.jaw5L.rotateAngleX = (float)Math.toRadians((double)d1) + Ljx;
		this.jaw1L.rotateAngleX = (float)Math.toRadians((double)d1) + Ljx;
		
		this.head7R.rotateAngleX = (float)Math.toRadians((double)d3);
		this.head3R.rotateAngleX = (float)Math.toRadians((double)d3);
		this.headfinR.rotateAngleX = (float)Math.toRadians((double)d3) + 0.5F;
		this.jaw5R.rotateAngleX = (float)Math.toRadians((double)d3) + Rjx;
		this.jaw1R.rotateAngleX = (float)Math.toRadians((double)d3) + Rjx;
		
		
		
		
		d1 = (float)c.getHead1Ext();
		d2 = (float)c.getHead2Ext();
		d3 = (float)c.getHead3Ext();
		
		this.neck3L.rotateAngleX = -((float)Math.toRadians((double)d1 / 3.0D));
		this.neck4L.rotateAngleX = -((float)Math.toRadians((double)d1 * 2.0D / 3.0D));
		this.neck5L.rotateAngleX = -((float)Math.toRadians((double)d1));
		this.neck3.rotateAngleX = -((float)Math.toRadians((double)d2 / 3.0D));
		this.neck4.rotateAngleX = -((float)Math.toRadians((double)d2 * 2.0D / 3.0D));
		this.neck5.rotateAngleX = -((float)Math.toRadians((double)d2));
		this.neck3R.rotateAngleX = -((float)Math.toRadians((double)d3 / 3.0D));
		this.neck4R.rotateAngleX = -((float)Math.toRadians((double)d3 * 2.0D / 3.0D));
		this.neck5R.rotateAngleX = -((float)Math.toRadians((double)d3));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		this.neck4.rotationPointY = this.neck3.rotationPointY + (float)Math.sin((double)this.neck3.rotateAngleX) * 9.0F;
		this.neck4.rotationPointZ = this.neck3.rotationPointZ - (float)Math.cos((double)this.neck3.rotateAngleX) * 9.0F;
		this.neck4.rotationPointX = this.neck3.rotationPointX - (float)Math.sin((double)this.neck3.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck3.rotateAngleX);
		this.neck5.rotationPointY = this.neck4.rotationPointY + (float)Math.sin((double)this.neck4.rotateAngleX) * 9.0F;
		this.neck5.rotationPointZ = this.neck4.rotationPointZ - (float)Math.cos((double)this.neck4.rotateAngleX) * 9.0F;
		this.neck5.rotationPointX = this.neck4.rotationPointX - (float)Math.sin((double)this.neck4.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck4.rotateAngleX);
		this.head7.rotationPointY = this.neck5.rotationPointY + (float)Math.sin((double)this.neck5.rotateAngleX) * 9.0F;
		this.headfin.rotationPointY = this.jaw5.rotationPointY = this.jaw1.rotationPointY = this.head3.rotationPointY = this.head7.rotationPointY;
		this.head7.rotationPointZ = this.neck5.rotationPointZ - (float)Math.cos((double)this.neck5.rotateAngleX) * 9.0F;
		this.headfin.rotationPointZ = this.jaw5.rotationPointZ = this.jaw1.rotationPointZ = this.head3.rotationPointZ = this.head7.rotationPointZ;
		this.head7.rotationPointX = this.neck5.rotationPointX - (float)Math.sin((double)this.neck5.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck5.rotateAngleX);
		this.headfin.rotationPointX = this.jaw5.rotationPointX = this.jaw1.rotationPointX = this.head3.rotationPointX = this.head7.rotationPointX;
		
		this.neck4L.rotationPointY = this.neck3L.rotationPointY + (float)Math.sin((double)this.neck3L.rotateAngleX) * 9.0F;
		this.neck4L.rotationPointZ = this.neck3L.rotationPointZ - (float)Math.cos((double)this.neck3L.rotateAngleX) * 9.0F;
		this.neck4L.rotationPointX = this.neck3L.rotationPointX - (float)Math.sin((double)this.neck3L.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck3L.rotateAngleX);
		this.neck5L.rotationPointY = this.neck4L.rotationPointY + (float)Math.sin((double)this.neck4L.rotateAngleX) * 9.0F;
		this.neck5L.rotationPointZ = this.neck4L.rotationPointZ - (float)Math.cos((double)this.neck4L.rotateAngleX) * 9.0F;
		this.neck5L.rotationPointX = this.neck4L.rotationPointX - (float)Math.sin((double)this.neck4L.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck4L.rotateAngleX);
		this.head7L.rotationPointY = this.neck5L.rotationPointY + (float)Math.sin((double)this.neck5L.rotateAngleX) * 9.0F;
		this.headfinL.rotationPointY = this.jaw5L.rotationPointY = this.jaw1L.rotationPointY = this.head3L.rotationPointY = this.head7L.rotationPointY;
		this.head7L.rotationPointZ = this.neck5L.rotationPointZ - (float)Math.cos((double)this.neck5L.rotateAngleX) * 9.0F;
		this.headfinL.rotationPointZ = this.jaw5L.rotationPointZ = this.jaw1L.rotationPointZ = this.head3L.rotationPointZ = this.head7L.rotationPointZ;
		this.head7L.rotationPointX = this.neck5L.rotationPointX - (float)Math.sin((double)this.neck5L.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck5L.rotateAngleX);
		this.headfinL.rotationPointX = this.jaw5L.rotationPointX = this.jaw1L.rotationPointX = this.head3L.rotationPointX = this.head7L.rotationPointX;
		
		this.neck4R.rotationPointY = this.neck3R.rotationPointY + (float)Math.sin((double)this.neck3R.rotateAngleX) * 9.0F;
		this.neck4R.rotationPointZ = this.neck3R.rotationPointZ - (float)Math.cos((double)this.neck3R.rotateAngleX) * 9.0F;
		this.neck4R.rotationPointX = this.neck3R.rotationPointX - (float)Math.sin((double)this.neck3R.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck3R.rotateAngleX);
		this.neck5R.rotationPointY = this.neck4R.rotationPointY + (float)Math.sin((double)this.neck4R.rotateAngleX) * 9.0F;
		this.neck5R.rotationPointZ = this.neck4R.rotationPointZ - (float)Math.cos((double)this.neck4R.rotateAngleX) * 9.0F;
		this.neck5R.rotationPointX = this.neck4R.rotationPointX - (float)Math.sin((double)this.neck4R.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck4R.rotateAngleX);
		this.head7R.rotationPointY = this.neck5R.rotationPointY + (float)Math.sin((double)this.neck5R.rotateAngleX) * 9.0F;
		this.headfinR.rotationPointY = this.jaw5R.rotationPointY = this.jaw1R.rotationPointY = this.head3R.rotationPointY = this.head7R.rotationPointY;
		this.head7R.rotationPointZ = this.neck5R.rotationPointZ - (float)Math.cos((double)this.neck5R.rotateAngleX) * 9.0F;
		this.headfinR.rotationPointZ = this.jaw5R.rotationPointZ = this.jaw1R.rotationPointZ = this.head3R.rotationPointZ = this.head7R.rotationPointZ;
		this.head7R.rotationPointX = this.neck5R.rotationPointX - (float)Math.sin((double)this.neck5R.rotateAngleY) * 9.0F * (float)Math.cos((double)this.neck5R.rotateAngleX);
		this.headfinR.rotationPointX = this.jaw5R.rotationPointX = this.jaw1R.rotationPointX = this.head3R.rotationPointX = this.head7R.rotationPointX;
		
		
		c.setRenderInfo(r);
		
		
		this.body.render(f5);
		this.leftleg1.render(f5);
		this.tail1.render(f5);
		this.leftleg2.render(f5);
		this.body2.render(f5);
		this.leftleg3.render(f5);
		this.tail2.render(f5);
		this.tail3.render(f5);
		this.lclaw2.render(f5);
		this.lclaw4.render(f5);
		this.lclaw5.render(f5);
		this.lclaw6.render(f5);
		this.lclaw7.render(f5);
		this.tail4.render(f5);
		this.tail5.render(f5);
		this.neck1.render(f5);
		this.neck3.render(f5);
		this.head3.render(f5);
		this.jaw1.render(f5);
		this.jaw5.render(f5);
		this.head7.render(f5);
		this.rightleg1.render(f5);
		this.rightleg2.render(f5);
		this.rightleg3.render(f5);
		this.rclaw2.render(f5);
		this.rclaw4.render(f5);
		this.rclaw5.render(f5);
		this.rclaw7.render(f5);
		this.rclaw6.render(f5);
		this.wing1.render(f5);
		this.wing2.render(f5);
		this.lshoulder.render(f5);
		this.rshoulder.render(f5);
		this.rwing1.render(f5);
		this.rwing2.render(f5);
		this.neck4.render(f5);
		this.neck5.render(f5);
		this.wing3.render(f5);
		this.rwing3.render(f5);
		this.wing4.render(f5);
		this.rwing4.render(f5);
		this.Tailspike1.render(f5);
		this.Tailspike2.render(f5);
		this.Tailspike3.render(f5);
		this.headfin.render(f5);
		this.backfin1.render(f5);
		this.backfin2.render(f5);
		this.neck3L.render(f5);
		this.neck4L.render(f5);
		this.neck3R.render(f5);
		this.neck4R.render(f5);
		this.neck5L.render(f5);
		this.neck5R.render(f5);
		this.jaw5L.render(f5);
		this.jaw5R.render(f5);
		this.head7L.render(f5);
		this.headfinL.render(f5);
		this.headfinR.render(f5);
		this.head7R.render(f5);
		this.jaw1L.render(f5);
		this.jaw1R.render(f5);
		this.head3L.render(f5);
		this.head3R.render(f5);
		
		GL11.glPushMatrix();
		GL11.glEnable(2977);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		
		GL11.glColor4f(0.75F, 0.75F, 0.75F, 0.55F);
		this.mem1.render(f5);
		this.mem2.render(f5);
		this.rmem1.render(f5);
		this.rmem2.render(f5);
		this.mem3.render(f5);
		this.rmem3.render(f5);
		this.mem4.render(f5);
		this.rmem4.render(f5);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
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
