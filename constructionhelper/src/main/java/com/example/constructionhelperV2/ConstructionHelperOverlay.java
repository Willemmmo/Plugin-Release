package com.example.constructionhelperV2;

import com.openosrs.client.ui.overlay.components.table.TableAlignment;
import com.openosrs.client.ui.overlay.components.table.TableComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import javax.swing.table.TableColumn;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.MenuAction;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class ConstructionHelperOverlay extends Overlay
{
	private final Client client;
	private final ConstructionHelperConfig config;
	private final ConstructionHelperPlugin plugin;
	private final PanelComponent panelComponent = new PanelComponent();

	@Inject
	private ConstructionHelperOverlay(Client client, ConstructionHelperConfig config, ConstructionHelperPlugin plugin)
	{
		super(plugin);
		this.plugin = plugin;
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.BOTTOM_LEFT);
		setPriority(OverlayPriority.HIGHEST);
		this.getMenuEntries().add(new OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "NMZ Helper Overlay"));
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if(plugin == null)
		{
			return null;
		}
		panelComponent.getChildren().clear();

		TableComponent tableComponent = new TableComponent();
		tableComponent.setColumnAlignments(TableAlignment.LEFT);
		tableComponent.setDefaultColor(Color.CYAN);
		tableComponent.addRow("Construction Helper");
		tableComponent.addRow(plugin.status);

		if(!tableComponent.isEmpty())
		{
			panelComponent.getChildren().add(tableComponent);
		}
		panelComponent.setPreferredSize(new Dimension(200,100));
		panelComponent.setBackgroundColor(Color.BLACK);
		return panelComponent.render(graphics);
	}
}
