package com.example.onetickboltenchant;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("OneTickBoltEnchantConfig")

public interface OneTickBoltEnchantConfig extends Config
{
	@ConfigItem(
		keyName = "label1",
		name = "Hotkey Setup",
		description = "",
		position = 0
	)
	default String label1()
	{
		return "Label1";
	}


	@ConfigItem(
		keyName = "example",
		name = "Example config item",
		description = "Example",
		position = 1
	)
	default boolean example()
	{
		return true;
	}
}