package com.cubic_control.c_companions.gui;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.c_companions.main.RefStrings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiInventory extends InventoryEffectRenderer{
	private static final ResourceLocation resourceLocation = new ResourceLocation(RefStrings.modid + ":textures/gui/inventory.png");
	
	private final EntityCompanion companion;
	
	private final int inventoryRows;

	public GuiInventory(EntityCompanion comp, IInventory playerInv, IInventory compInventory) {
		super(new ContainerInventory(playerInv, compInventory, comp));
		
		companion = comp;
		this.allowUserInput = false;
		
		final char c = '\336';
		final int i = c - 108;
		inventoryRows = compInventory.getSizeInventory() / 9;
		xSize = xSize + 24;
		ySize = i + inventoryRows * 18;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		mc.getTextureManager().bindTexture(resourceLocation);
		
		int addX = Minecraft.getMinecraft().thePlayer.getActivePotionEffects().size() > 0 ? 120 : 0;
		
		final int x = (width - xSize + addX) / 2;
		final int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize + 26, inventoryRows * 18 + 21);
		drawTexturedModalRect(x, y + inventoryRows * 18 + 17, 0, 126, xSize + 26, 96); //Player
	}
	
	@Override
	protected void keyTyped(char c, int keyCode) {
		if(keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode())
		{
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		companion.getInventory().closeInventory();
	}

}
