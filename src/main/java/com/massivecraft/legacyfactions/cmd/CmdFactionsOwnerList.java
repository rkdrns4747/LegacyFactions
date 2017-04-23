package com.massivecraft.legacyfactions.cmd;

import com.massivecraft.legacyfactions.FLocation;
import com.massivecraft.legacyfactions.Permission;
import com.massivecraft.legacyfactions.TL;
import com.massivecraft.legacyfactions.entity.Board;
import com.massivecraft.legacyfactions.entity.Conf;


public class CmdFactionsOwnerList extends FCommand {

    public CmdFactionsOwnerList() {
        super();
        this.aliases.add("ownerlist");

        //this.requiredArgs.add("");
        //this.optionalArgs.put("", "");

        this.permission = Permission.OWNERLIST.node;
        this.disableOnLock = false;

        senderMustBePlayer = true;
        senderMustBeMember = false;
        senderMustBeModerator = false;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform() {
        boolean hasBypass = fme.isAdminBypassing();

        if (!hasBypass && !assertHasFaction()) {
            return;
        }

        if (!Conf.ownedAreasEnabled) {
            fme.msg(TL.COMMAND_OWNERLIST_DISABLED);
            return;
        }

        FLocation flocation = new FLocation(fme);

        if (Board.getInstance().getFactionAt(flocation) != myFaction) {
            if (!hasBypass) {
                fme.msg(TL.COMMAND_OWNERLIST_WRONGFACTION);
                return;
            }
            
            myFaction = Board.getInstance().getFactionAt(flocation);
            if (!myFaction.isNormal()) {
                fme.msg(TL.COMMAND_OWNERLIST_NOTCLAIMED);
                return;
            }
        }

        String owners = myFaction.getOwnerListString(flocation);

        if (owners == null || owners.isEmpty()) {
            fme.msg(TL.COMMAND_OWNERLIST_NONE);
            return;
        }

        fme.msg(TL.COMMAND_OWNERLIST_OWNERS, owners);
    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_OWNERLIST_DESCRIPTION;
    }
}
