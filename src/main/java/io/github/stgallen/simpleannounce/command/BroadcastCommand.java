package io.github.stgallen.simpleannounce.command;

import com.google.inject.Inject;
import com.mojang.brigadier.context.CommandContext;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class BroadcastCommand {

  @Inject
  private ProxyServer proxyServer;

  public int execute(CommandContext<CommandSource> ctx) {
    Component deserialized = Component.text()
      .append(LegacyComponentSerializer.legacyAmpersand().deserialize("&a" + ctx.getArgument("message", String.class)))
      .build();
    proxyServer.getAllPlayers().forEach(player -> player.sendMessage(deserialized));
    return 1;
  }
}
