/* Copyright (c) 2021, Willemmmo <https://github.com/Willemmmo> Plugin Developer
 * Copyright (c) 2021, Andrew EP | ElPinche256 <https://github.com/ElPinche256> for providing the "JavaExample" Plugin
 * Copyright (c) 2021, Ben93Riggs <https://github.com/ben93riggs> for opensource sharing how to use commands/general java
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.example.constructionhelperV2;

import java.awt.event.KeyEvent;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.ConfigTitle;
import net.runelite.client.config.Keybind;

@ConfigGroup("ConstructionHelperConfig")

public interface ConstructionHelperConfig extends Config
{
	@ConfigTitle(
		keyName = "ConstructionHelperV2",
		name = "How to use this plugin?",
		description = "This plugin will send keyevents to help you with semi-manual construction",
		position = 0
	)

	@ConfigSection(
		keyName = "DialogSetup1",
		name = "Dialog Setup",
		description = "Dialog Setup for removing items/using butler",
		position = 1
	)
	String DialogSetup1 = "Dialog_Setup";
	@ConfigSection(
		keyName = "ConstructionWidgetSetup",
		name = "Construction Widget Setup",
		description = "Setup actions Construction Widget",
		position = 3
	)
	String ConstructionWidgetSetup = "Construction_Widget_Setup";
	@ConfigSection(
		keyName = "Butler_Setup",
		name = "Butler Setup",
		description = "Setup the butler",
		position = 2
	)
	String Butler_Setup = "Butler_Setup";
	@ConfigSection(
		keyName = "Developer_Options",
		name = "Developer Options",
		description = "Additional Options for Pro users",
		position = 100
	)
	String Developer_Options = "DeveloperOptions";

	@ConfigItem(
		keyName = "EnableDialogKey",
		name = "Enable Dialog Key",
		description = "Allows Dialog key use",
		position = 0,
		section = DialogSetup1
	)
	default boolean EnableDialogKey()
	{
		return false;
	}
	@ConfigItem(
		keyName = "DialogHotkey1",
		name = "Press key on Dialog",
		description = "Press this key on dialog",
		position = 1,
		section = DialogSetup1,
		hidden = true,
		unhide = "EnableDialogKey",
		unhideValue = "true"
	)
	default Keybind DialogHotkey1()
	{
		return new Keybind(KeyEvent.VK_1, 0);
	}

	@ConfigItem(
		keyName = "Key1_Script",
		name = "Lines to press Key 1",
		description = "Fill in lines",
		position = 2,
		section = DialogSetup1,
		hidden = true,
		unhide = "EnableDialogKey",
		unhideValue = "true"
	)
	default String Key1_Script()
	{
		return new String("Fetch from bank\nRepeat last task?\nReally remove it?\nOkay, here's");
	}
	@ConfigItem(
		keyName = "EnableButlerKey",
		name = "Enable Butler Key",
		description = "Allows butler key use",
		position = 0,
		section = Butler_Setup
	)
	default boolean EnableButlerKey()
	{
		return false;
	}
	@ConfigItem(
		keyName = "ButlerHotkey",
		name = "Press key on Butler",
		description = "Press key on Butler",
		position = 1,
		section = Butler_Setup,
		hidden = true,
		unhide = "EnableButlerKey",
		unhideValue = "true"
	)
	default Keybind ButlerHotkey()
	{
		return new Keybind(KeyEvent.VK_SPACE, 0);
	}

	@ConfigItem(
		keyName = "Key2_Script",
		name = "Press butler key",
		description = "Fill in lines",
		position = 2,
		section = Butler_Setup,
		hidden = true,
		unhide = "EnableButlerKey",
		unhideValue = "true"
	)
	default String Key2_Script()
	{
		return new String("Click here to continue\nMaster, if thou desirest my\nThank you, Master");
	}

	@ConfigItem(
		keyName = "Key3_Script",
		name = "Press nothing/fetch data",
		description = "Fill in lines",
		position = 3,
		section = Butler_Setup
	)
	default String Key3_Script()
	{
		return new String("Master, I have returned with what you asked me to\nMaster, I have returned with what thou asked me to");
	}
	@ConfigItem(
		keyName = "EnableConstructionKey",
		name = "Enable Construction Key",
		description = "Allows constrution key use",
		position = 0,
		section = ConstructionWidgetSetup
	)
	default boolean EnableConstructionKey()
	{
		return false;
	}
	@ConfigItem(
		keyName = "ConstructionHotkey",
		name = "Press key on Construction Widget",
		description = "Press this key on Construction Widget",
		position = 1,
		section = ConstructionWidgetSetup,
		hidden = true,
		unhide = "EnableConstructionKey",
		unhideValue = "true"
	)
	default Keybind ConstructionHotkey()
	{
		return new Keybind(KeyEvent.VK_3, 0);
	}

	@ConfigItem(
		keyName = "EnableDeveloper",
		name = "Enable Developer Options",
		description = "Additional Options for Pro users",
		position = 0,
		section = Developer_Options
	)
	default boolean EnableDeveloper()
	{
		return false;
	}

	@ConfigItem(
		keyName = "DialogWidgetID",
		name = "The WidgetID for the Dialog",
		description = "The WidgetID for the Dialog",
		position = 0,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)

	default int DialogWidgetID()
	{
		return 219;
	}

	@ConfigItem(
		keyName = "DialogWidgetChildID",
		name = "The ChildID for the Dialog",
		description = "ChildID for the Dialog",
		position = 1,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)

	default int DialogWidgetChildID()
	{
		return 1;
	}

	@ConfigItem(
		keyName = "ButlerWidgetID",
		name = "The WidgetID for Butler",
		description = "The WidgetID for Butler",
		position = 2,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)

	default int ButlerWidgetID()
	{
		return 231;
	}

	@ConfigItem(
		keyName = "ButlerWidgetChildID",
		name = "The ChildID for Butler",
		description = "ChildID for Butler",
		position = 3,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)

	default int ButlerWidgetChildID()
	{
		return 5;
	}

	@ConfigItem(
		keyName = "PlankID_MIN",
		name = "Offsets for planks message",
		description = "Removes useless chars",
		position = 4,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)
	default int PlankID_MIN()
	{
		return 159;
	}

	@ConfigItem(
		keyName = "PlankID_MAX",
		name = "Offsets for planks message",
		description = "Removes useless chars",
		position = 5,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)
	default int PlankID_MAX()
	{
		return 161;
	}

	@ConfigItem(
		keyName = "ConstructionWidgetID",
		name = "The WidgetID for Building stuff",
		description = "WidgetID for Building stuff",
		position = 6,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)

	default int ConstructionWidgetID()
	{
		return 458;
	}

	@ConfigItem(
		keyName = "ConstructionWidgetChildID",
		name = "The ChildID for Building stuff",
		description = "ChildID for Building stuff",
		position = 7,
		section = Developer_Options,
		hidden = true,
		unhide = "EnableDeveloper",
		unhideValue = "true"
	)

	default int ConstructionWidgetChildID()
	{
		return 0;
	}
}