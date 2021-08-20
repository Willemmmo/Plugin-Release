package com.example.onetickboltenchant.ScriptCommand;

import java.util.Objects;
import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

class WidgetHook
{
	WidgetInfo MageTab(Client client)
	{
		try
		{
			Widget temp = client.getWidget(WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB);
			if (client.getWidget(WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB) == null || Objects.requireNonNull(temp).isHidden())
			{
				temp = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_TAB);
				if (client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_TAB) == null || Objects.requireNonNull(temp).isHidden())
				{
					temp = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_TAB);
					if (client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_TAB) == null || Objects.requireNonNull(temp).isHidden())
					{
						System.out.println("View not found");
						return null;
					}
					else
					{
						return WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_TAB;
					}
				}
				else
				{
					return WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_TAB;
				}
			}
			else
			{
				return WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	boolean isTabOpen(Client client, WidgetInfo widgetInfo){
		if (widgetInfo == null)
		{
			return false;
		}
		Widget tab = client.getWidget(widgetInfo);
		return !Objects.requireNonNull(tab).isHidden() && tab.getSpriteId() < 0;
	}
	WidgetInfo InventoryTab(Client client)
	{
		try
		{
			Widget temp = client.getWidget(WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB);
			if (client.getWidget(WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB) == null || Objects.requireNonNull(temp).isHidden())
			{
				temp = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB);
				if (client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB) == null || Objects.requireNonNull(temp).isHidden())
				{
					temp = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);
					if (client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB) == null || Objects.requireNonNull(temp).isHidden())
					{
						System.out.println("View not found");
						return null;
					}
					else
					{
						return WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB;
					}
				}
				else
				{
					return WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB;
				}
			}
			else
			{
				return WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
