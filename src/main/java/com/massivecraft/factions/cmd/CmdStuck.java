package com.massivecraft.factions.cmd;

import com.massivecraft.factions.*;
import com.massivecraft.factions.integration.essentials.EssentialsEngine;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.util.SpiralTask;
import com.massivecraft.factions.zcore.util.TL;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CmdStuck extends FCommand {

    public CmdStuck() {
        super();

        this.aliases.add("stuck");
        this.aliases.add("halp!"); // halp! c:

        this.permission = Permission.STUCK.node;
        this.disableOnLock = true;

        senderMustBePlayer = false;
        senderMustBeMember = false;
        senderMustBeModerator = false;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform() {
        final Player player = fme.getPlayer();
        final Location sentAt = player.getLocation();
        final FLocation chunk = fme.getLastStoodAt();
        final long delay = P.get().getConfig().getLong("hcf.stuck.delay", 30);
        final int radius = P.get().getConfig().getInt("hcf.stuck.radius", 10);

        if (P.get().getStuckMap().containsKey(player.getUniqueId())) {
            long wait = P.get().getTimers().get(player.getUniqueId()) - System.currentTimeMillis();
            String time = DurationFormatUtils.formatDuration(wait, TL.COMMAND_STUCK_TIMEFORMAT.toString(), true);
            msg(TL.COMMAND_STUCK_EXISTS, time);
        } else {

            // if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
            if (!payForCommand(Conf.econCostStuck, TL.COMMAND_STUCK_TOSTUCK.format(fme.getName()), TL.COMMAND_STUCK_FORSTUCK.format(fme.getName()))) {
                return;
            }

            final int id = Bukkit.getScheduler().runTaskLater(P.get(), new BukkitRunnable() {

                @Override
                public void run() {
                    if (!P.get().getStuckMap().containsKey(player.getUniqueId())) {
                        return;
                    }

                    // check for world difference or radius exceeding
                    final World world = chunk.getWorld();
                    if (world.getUID() != player.getWorld().getUID() || sentAt.distance(player.getLocation()) > radius) {
                        msg(TL.COMMAND_STUCK_OUTSIDE.format(radius));
                        P.get().getTimers().remove(player.getUniqueId());
                        P.get().getStuckMap().remove(player.getUniqueId());
                        return;
                    }

                    final Board board = Board.getInstance();
                    // spiral task to find nearest wilderness chunk
                    new SpiralTask(new FLocation(me), radius * 2) {

                        @Override
                        public boolean work() {
                            FLocation chunk = currentFLocation();
                            Faction faction = board.getFactionAt(chunk);
                            if (faction.isWilderness()) {
                                int cx = FLocation.chunkToBlock((int) chunk.getX());
                                int cz = FLocation.chunkToBlock((int) chunk.getZ());
                                int y = world.getHighestBlockYAt(cx, cz);
                                Location tp = new Location(world, cx, y, cz);
                                msg(TL.COMMAND_STUCK_TELEPORT, tp.getBlockX(), tp.getBlockY(), tp.getBlockZ());
                                P.get().getTimers().remove(player.getUniqueId());
                                P.get().getStuckMap().remove(player.getUniqueId());
                                if (!EssentialsEngine.handleTeleport(player, tp)) {
                                    player.teleport(tp);
                                    P.get().debug("/f stuck used regular teleport, not essentials!");
                                }
                                this.stop();
                                return false;
                            }
                            return true;
                        }
                    };
                }
            }, delay * 20).getTaskId();

            P.get().getTimers().put(player.getUniqueId(), System.currentTimeMillis() + (delay * 1000));
            long wait = P.get().getTimers().get(player.getUniqueId()) - System.currentTimeMillis();
            String time = DurationFormatUtils.formatDuration(wait, TL.COMMAND_STUCK_TIMEFORMAT.toString(), true);
            msg(TL.COMMAND_STUCK_START, time);
            P.get().getStuckMap().put(player.getUniqueId(), id);
        }
    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_STUCK_DESCRIPTION;
    }
}
