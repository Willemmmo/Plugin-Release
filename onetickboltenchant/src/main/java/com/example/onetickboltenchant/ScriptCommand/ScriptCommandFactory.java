package com.example.onetickboltenchant.ScriptCommand;

public class ScriptCommandFactory
{
	public static ScriptCommand builder(final String scriptCommand)
	{
		switch (scriptCommand.toLowerCase())
		{
			case "enchantbolt":
				return new EnchantBoltCommand();
			case "openmage":
				return new OpenMageTabCommand();
			default:
				if (scriptCommand.toLowerCase().startsWith("group"))
				{
					return new ExceptionCommand();
				}
				else
				{
					return new ExceptionCommand();
				}
		}
	}
}
