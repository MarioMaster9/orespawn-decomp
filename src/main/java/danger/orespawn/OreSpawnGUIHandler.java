package danger.orespawn;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;





public class OreSpawnGUIHandler implements IGuiHandler
{
	
	
	
	
	
	/**
	 * Returns a Server side Container to be displayed to the user.
	 *
	 * @param ID The Gui ID Number
	 * @param player The player viewing the Gui
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z Position
	 * @return A GuiScreen/Container to be displayed to the user, null if none.
	 */
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		switch (ID) {
			case 0:
				if (tileEntity instanceof TileEntityCrystalFurnace) {
					return new ContainerCrystalFurnace(player.inventory, (TileEntityCrystalFurnace)tileEntity);
				} else {
					break;
				}
			case 1:
				return new ContainerCrystalWorkbench(player.inventory, world, x, y, z);
			default:
				break;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * Returns a Container to be displayed to the user. On the client side, this
	 * needs to return a instance of GuiScreen On the server side, this needs to
	 * return a instance of Container
	 *
	 * @param ID The Gui ID Number
	 * @param player The player viewing the Gui
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z Position
	 * @return A GuiScreen/Container to be displayed to the user, null if none.
	 */
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		switch (ID) {
			case 0:
				if (tileEntity instanceof TileEntityCrystalFurnace) {
					return new CrystalFurnaceGUI(player.inventory, (TileEntityCrystalFurnace)tileEntity);
				}
				break;
			case 1:
				return new CrystalWorkbenchGUI(player.inventory, world, x, y, z);
			default:
				break;
		}
		return null;
	}
}
