package net.redstoneore.legacyfactions.cmd;

import net.redstoneore.legacyfactions.Permission;
import net.redstoneore.legacyfactions.Lang;
import net.redstoneore.legacyfactions.entity.Conf;

public class CmdFactionsBoom extends FCommand {

    public CmdFactionsBoom() {
        super();
        this.aliases.add("noboom");
        this.aliases.add("explosions");
        this.aliases.add("toggleexplosions");

        //this.requiredArgs.add("");
        this.optionalArgs.put("on/off", "flip");

        this.permission = Permission.NO_BOOM.node;
        this.disableOnLock = true;

        senderMustBePlayer = true;
        senderMustBeMember = false;
        senderMustBeModerator = true;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform() {
        if (!myFaction.isPeaceful()) {
            fme.msg(Lang.COMMAND_BOOM_PEACEFULONLY);
            return;
        }

        // if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
        if (!payForCommand(Conf.econCostNoBoom, Lang.COMMAND_BOOM_TOTOGGLE, Lang.COMMAND_BOOM_FORTOGGLE)) {
            return;
        }

        myFaction.setPeacefulExplosionsEnabled(this.argAsBool(0, !myFaction.getPeacefulExplosionsEnabled()));

        String enabled = myFaction.noExplosionsInTerritory() ? Lang.GENERIC_DISABLED.toString() : Lang.GENERIC_ENABLED.toString();

        // Inform
        myFaction.msg(Lang.COMMAND_BOOM_ENABLED, fme.describeTo(myFaction), enabled);
    }

    @Override
    public String getUsageTranslation() {
        return Lang.COMMAND_BOOM_DESCRIPTION.toString();
    }
}
