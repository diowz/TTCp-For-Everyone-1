package tudbut.mod.client.ttcp.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import tudbut.tools.Lock;

public class KillSwitch$GuiKilled
extends GuiScreen {
    ScaledResolution sr;
    int y;
    Lock timer;

    public KillSwitch$GuiKilled() {
        this.mc = Minecraft.getMinecraft();
        this.sr = new ScaledResolution(this.mc);
        this.y = this.sr.getScaledHeight() / 3;
        this.timer = new Lock();
        this.timer.lock(15000);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
    }

    public void onGuiClosed() {
        new Thread(() -> {
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)this);
        }).start();
        super.onGuiClosed();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.sr = new ScaledResolution(this.mc);
        this.y = this.sr.getScaledHeight() / 3;
        KillSwitch$GuiKilled.func_73734_a((int)0, (int)0, (int)this.sr.getScaledWidth(), (int)this.sr.getScaledHeight(), (int)-16777216);
        if (this.timer.isLocked()) {
            Mouse.setGrabbed((boolean)true);
        }
        this.drawString("Your TTC has been " + type + " by a developer.");
        this.drawString("Please contact us on the discord (https://discord.gg/UgbPQvyfmc)");
        this.drawString("or at TudbuT#2624!!!");
        this.drawString("Your mouse will be grabbed for about 15 more");
        this.drawString("seconds, afterwards, your minecraft will exit...");
        if (!this.timer.isLocked()) {
            Mouse.setGrabbed((boolean)false);
            Minecraft.getMinecraft().shutdown();
        }
    }

    private void drawString(String s) {
        String[] lines = s.split("\n");
        for (int i = 0; i < lines.length; ++i) {
            s = lines[i];
            this.mc.fontRenderer.drawString("§l" + s, (float)this.sr.getScaledWidth() / 2.0f - (float)this.mc.fontRenderer.getStringWidth("§l" + s) / 2.0f, (float)this.y, -65536, true);
            this.y += 13;
        }
    }
}
