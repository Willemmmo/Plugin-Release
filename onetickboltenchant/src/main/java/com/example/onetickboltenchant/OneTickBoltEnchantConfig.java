package com.example.onetickboltenchant;

import java.awt.event.KeyEvent;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.ConfigTitle;
import net.runelite.client.config.Keybind;

@ConfigGroup("OneTickBoltEnchantConfig")

public interface OneTickBoltEnchantConfig extends Config
{
	@ConfigTitle(
		keyName = "Explain1",
		name = "How to use this plugin?",
		description = "To use this plugin press the hotkey and hold space bar down.",
		position = 0
	)

	@ConfigSection(
		keyName = "Title1",
		name = "HotkeySetup",
		description = "What hotkeys you want to use",
		position = 1
	)
	String Title1 = "HotkeySetup";

	@ConfigItem(
		keyName = "key1",
		name = "Key to start Enchanting",
		description = "Press this key to start enchanting bolts",
		position = 0,
		section = Title1
	)
	default Keybind key1()
	{
		return new Keybind(KeyEvent.VK_1, 0);
	}

	@ConfigItem(
		keyName = "key2",
		name = "Key to stop Enchanting",
		description = "Press this key to stop enchanting bolts",
		position = 1,
		section = Title1
	)
	default Keybind key2()
	{
		return new Keybind(KeyEvent.VK_2, 0);
	}

	@ConfigSection(
		keyName = "Title2",
		name = "Settings",
		description = "Settings for this plugin",
		position = 2
	)
	String Title2 = "Settings";
	@ConfigItem(
		keyName = "forcemagetab",
		name = "Force magic tab",
		description = "Forces client to open magic tab",
		position = 0,
		section = Title2
	)
	default boolean forcemagetab()
	{
		return true;
	}
	/*
	@ConfigItem(
		keyName = "checkbolts",
		name = "Check for Bolts",
		description = "Check inventory for bolts",
		position = 1,
		section = Title2
	)
	default boolean checkbolts() {
		return true;
	}
	@ConfigItem(
		keyName = "missrandom",
		name = "Miss random ticks",
		description = "Misses random ticks",
		position = 2,
		section = Title2
	)
	default boolean missrandom()
	{
		return true;
	}
	@ConfigItem(
		keyName = "mincast",
		name = "Minimal correct casts",
		description = "Minimum casts for random miss calculation",
		position = 3,
		section = Title2
	)
	default int mincast()
	{
		return 10;
	}
	@ConfigItem(
		keyName = "maxcast",
		name = "Maximal correct casts",
		description = "Maximal casts for random miss calculation",
		position = 4,
		section = Title2
	)
	default int maxcast()
	{
		return 100;
	}*/
}