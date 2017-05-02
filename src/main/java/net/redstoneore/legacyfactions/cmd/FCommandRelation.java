package net.redstoneore.legacyfactions.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import net.redstoneore.legacyfactions.Permission;
import net.redstoneore.legacyfactions.Relation;
import net.redstoneore.legacyfactions.Lang;
import net.redstoneore.legacyfactions.entity.Conf;
import net.redstoneore.legacyfactions.entity.Faction;
import net.redstoneore.legacyfactions.event.EventFactionsRelation;
import net.redstoneore.legacyfactions.event.EventFactionsRelationChange;
import net.redstoneore.legacyfactions.scoreboards.FTeamWrapper;

public abstract class FCommandRelation extends FCommand {

    public Relation targetRelation;

    public FCommandRelation() {
        this.requiredArgs.add("faction tag");
        //this.optionalArgs.put("player name", "you");

        this.permission = Permission.RELATION.node;
        this.disableOnLock = true;

        senderMustBePlayer = true;
        senderMustBeMember = false;
        senderMustBeModerator = true;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform() {
        Faction them = this.argAsFaction(0);
        if (them == null) {
            return;
        }

        if (!them.isNormal()) {
            msg(Lang.COMMAND_RELATIONS_ALLTHENOPE);
            return;
        }

        if (them == myFaction) {
            msg(Lang.COMMAND_RELATIONS_MORENOPE);
            return;
        }

        if (myFaction.getRelationWish(them) == targetRelation) {
            msg(Lang.COMMAND_RELATIONS_ALREADYINRELATIONSHIP, them.getTag());
            return;
        }

        if (hasMaxRelations(them, targetRelation)) {
            // We message them down there with the count.
            return;
        }
        Relation oldRelation = myFaction.getRelationTo(them, true);
        EventFactionsRelationChange wishEvent = new EventFactionsRelationChange(fme, myFaction, them, oldRelation, targetRelation);
        Bukkit.getPluginManager().callEvent(wishEvent);
        if (wishEvent.isCancelled()) {
            return;
        }

        // if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
        if (!payForCommand(targetRelation.getRelationCost(), Lang.COMMAND_RELATIONS_TOMARRY, Lang.COMMAND_RELATIONS_FORMARRY)) {
            return;
        }

        // try to set the new relation
        myFaction.setRelationWish(them, targetRelation);
        Relation currentRelation = myFaction.getRelationTo(them, true);
        ChatColor currentRelationColor = currentRelation.getColor();

        // if the relation change was successful
        if (targetRelation.value == currentRelation.value) {
            // trigger the faction relation event
            EventFactionsRelation relationEvent = new EventFactionsRelation(myFaction, them, oldRelation, currentRelation);
            Bukkit.getServer().getPluginManager().callEvent(relationEvent);

            them.msg(Lang.COMMAND_RELATIONS_MUTUAL, currentRelationColor + targetRelation.getTranslation(), currentRelationColor + myFaction.getTag());
            myFaction.msg(Lang.COMMAND_RELATIONS_MUTUAL, currentRelationColor + targetRelation.getTranslation(), currentRelationColor + them.getTag());
        } else {
            // inform the other faction of your request
            them.msg(Lang.COMMAND_RELATIONS_PROPOSAL_1, currentRelationColor + myFaction.getTag(), targetRelation.getColor() + targetRelation.getTranslation());
            them.msg(Lang.COMMAND_RELATIONS_PROPOSAL_2, Conf.baseCommandAliases.get(0), targetRelation, myFaction.getTag());
            myFaction.msg(Lang.COMMAND_RELATIONS_PROPOSAL_SENT, currentRelationColor + them.getTag(), "" + targetRelation.getColor() + targetRelation);
        }

        if (!targetRelation.isNeutral() && them.isPeaceful()) {
            them.msg(Lang.COMMAND_RELATIONS_PEACEFUL);
            myFaction.msg(Lang.COMMAND_RELATIONS_PEACEFULOTHER);
        }

        if (!targetRelation.isNeutral() && myFaction.isPeaceful()) {
            them.msg(Lang.COMMAND_RELATIONS_PEACEFULOTHER);
            myFaction.msg(Lang.COMMAND_RELATIONS_PEACEFUL);
        }

        FTeamWrapper.updatePrefixes(myFaction);
        FTeamWrapper.updatePrefixes(them);
    }

    private boolean hasMaxRelations(Faction them, Relation rel) {
    	if (!Conf.maxRelations.containsKey(rel)) return false;
    	if (Conf.maxRelations.get(rel) < 0) return false;
    	
    	int maxRelations = Conf.maxRelations.get(rel);
    	
        if (this.myFaction.getRelationCount(rel) >= maxRelations) {
         	this.msg(Lang.COMMAND_RELATIONS_EXCEEDS_ME, maxRelations, rel.getPluralTranslation());
            return true;
        }
            
        if (them.getRelationCount(rel) > maxRelations) {
            this.msg(Lang.COMMAND_RELATIONS_EXCEEDS_THEY, maxRelations, rel.getPluralTranslation());
            return true;
        }
        
        return false;
    }

    @Override
    public String getUsageTranslation() {
        return Lang.COMMAND_RELATIONS_DESCRIPTION.toString();
    }
}
