package net.redstoneore.legacyfactions.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.redstoneore.legacyfactions.EconomyParticipator;
import net.redstoneore.legacyfactions.FLocation;
import net.redstoneore.legacyfactions.Relation;
import net.redstoneore.legacyfactions.RelationParticipator;
import net.redstoneore.legacyfactions.Role;
import net.redstoneore.legacyfactions.announcement.Announcements;
import net.redstoneore.legacyfactions.flag.Flag;
import net.redstoneore.legacyfactions.locality.Locality;
import net.redstoneore.legacyfactions.warp.FactionWarps;

public interface Faction extends EconomyParticipator {
	
	// -------------------------------------------------- //
	// FACTION
	// -------------------------------------------------- //

	String getId();

	String getTag();

	String getTag(String prefix);

	String getTag(Faction otherFaction);

	String getTag(FPlayer otherFplayer);

	void setTag(String str);

	String getComparisonTag();

	String getDescription();

	void setDescription(String value);
	
	// -------------------------------------------------- //
	// FLAGS
	// -------------------------------------------------- //
	
	Map<Flag, Boolean> getFlags();
	
	boolean setFlag(Flag flag, Boolean value);
	
	boolean getFlag(Flag flag);
	
	// -------------------------------------------------- //
	// WARPS
	// -------------------------------------------------- //

	FactionWarps warps();
	
	// -------------------------------------------------- //
	// VAULTS
	// -------------------------------------------------- //
	
	int getMaxVaults();

	void setMaxVaults(int value);
	
	// -------------------------------------------------- //
	// INVITES
	// -------------------------------------------------- //

	Set<String> getInvites();

	void invite(FPlayer fplayer);

	void deinvite(FPlayer fplayer);

	boolean isInvited(FPlayer fplayer);
	
	// -------------------------------------------------- //
	// BANS
	// -------------------------------------------------- //

	void ban(FPlayer fplayer);
	
	void unban(FPlayer fplayer);
	
	boolean isBanned(FPlayer fplayer);
	
	
	@Deprecated
	boolean isPeaceful();

	@Deprecated
	void setPeaceful(boolean isPeaceful);

	@Deprecated
	void setPeacefulExplosionsEnabled(boolean val);

	@Deprecated
	boolean getPeacefulExplosionsEnabled();

	@Deprecated
	boolean noExplosionsInTerritory();

	boolean noCreeperExplosions(Location location);

	@Deprecated
	boolean isPermanent();

	@Deprecated
	void setPermanent(boolean isPermanent);

	boolean hasForcedMapCharacter();
	
	void setForcedMapCharacter(char character);
	
	Character getForcedMapCharacter();
	
	boolean hasForcedMapColour();
	
	void setForcedMapColour(ChatColor colour);
	
	ChatColor getForcedMapColour();
	
	void setHome(Location home);

	boolean hasHome();

	Location getHome(Boolean checkValid);
	
	Location getHome();

	long getFoundedDate();

	void setFoundedDate(long newDate);

	void confirmValidHome();

	String getAccountId();

	Integer getPermanentPower();

	void setPermanentPower(Integer permanentPower);

	boolean hasPermanentPower();

	double getPowerBoost();

	void setPowerBoost(double powerBoost);

	boolean noPvPInTerritory();

	boolean noMonstersInTerritory();

	boolean isNormal();

	boolean isWilderness();

	boolean isSafeZone();

	boolean isWarZone();

	boolean isPlayerFreeType();

	boolean isPowerFrozen();

	void setLastDeath(long time);

	long getLastDeath();
		
	int getKills();

	int getDeaths();
	
	Announcements announcements();

	// -------------------------------
	// Relation and relation colors
	// -------------------------------

	@Override
	String describeTo(RelationParticipator that, boolean ucfirst);

	@Override
	String describeTo(RelationParticipator that);

	@Override
	Relation getRelationTo(RelationParticipator rp);

	@Override
	Relation getRelationTo(RelationParticipator rp, boolean ignorePeaceful);

	@Override
	ChatColor getColorTo(RelationParticipator rp);

	Relation getRelationWish(Faction otherFaction);

	void setRelationWish(Faction otherFaction, Relation relation);

	int getRelationCount(Relation relation);

	boolean hasMaxRelations(Faction them, Relation rel, Boolean silent);
	
	/**
	 * Returns a snapshot of current relationship wishes 
	 * @return
	 */
	Map<String, Relation> getRelationWishes();

	// ----------------------------------------------//
	// Power
	// ----------------------------------------------//
	double getPower();

	double getPowerMax();

	int getPowerRounded();

	int getPowerMaxRounded();

	int getLandRounded();

	int getLandRoundedInWorld(World world);

	boolean hasLandInflation();

	// -------------------------------------------------- //
	// FPlayers
	// -------------------------------------------------- //

	// maintain the reference list of FPlayers in this faction
	void refreshFPlayers();

	boolean addFPlayer(FPlayer fplayer);

	boolean removeFPlayer(FPlayer fplayer);

	int getSize();

	Set<FPlayer> getMembers();

	Set<FPlayer> getWhereOnline(boolean online);

	FPlayer getOwner();

	/**
	 * Deprecated, use {@link #getWhereRole(Role)}
	 * @param role
	 * @return
	 */
	@Deprecated
	ArrayList<FPlayer> getFPlayersWhereRole(Role role);
	
	List<FPlayer> getWhereRole(Role role);

	ArrayList<Player> getOnlinePlayers();

	// slightly faster check than getOnlinePlayers() if you just want to see if
	// there are any players online
	boolean hasPlayersOnline();

	void memberLoggedOff();

	// used when current leader is about to be removed from the faction;
	// promotes new leader, or disbands faction if no other members left
	void promoteNewLeader();

	// -------------------------------------------------- //
	// MESSAGES
	// -------------------------------------------------- //
	
	/**
	 * Send a message to the faction
	 * @param message
	 */
	void sendMessage(String message);

	void sendMessage(List<String> messages);
		
	void sendPlainMessage(String message);
	
	void sendPlainMessage(List<String> messages);

	// -------------------------------------------------- //
	// OWNERSHIP
	// -------------------------------------------------- //

	Map<FLocation, Set<String>> getClaimOwnership();

	void clearAllClaimOwnership();

	void clearClaimOwnership(Locality locality);
	void clearClaimOwnership(FLocation loc);
	void clearClaimOwnership(FPlayer player);

	int getCountOfClaimsWithOwners();

	boolean doesLocationHaveOwnersSet(FLocation loc);

	boolean isPlayerInOwnerList(FPlayer player, FLocation loc);

	void setPlayerAsOwner(FPlayer player, FLocation loc);

	void removePlayerAsOwner(FPlayer player, FLocation loc);

	Set<String> getOwnerList(FLocation loc);

	String getOwnerListString(FLocation loc);

	boolean playerHasOwnershipRights(FPlayer fplayer, FLocation loc);


	// -------------------------------------------------- //
	// Persistance and entity management
	// -------------------------------------------------- //
	
	void remove();

	Set<FLocation> getAllClaims();

	void setId(String id);
	
	// -------------------------------------------------- //
	// DEPRECATED
	// -------------------------------------------------- //
	
	/**
	 * Deprecated, use {@link #getOwner()}
	 */
	@Deprecated
	FPlayer getFPlayerAdmin();

	/**
	 * Deprecated, use {@link #getWhereOnline(boolean)}
	 *
	 * @param online
	 * @return
	 */
	@Deprecated
	Set<FPlayer> getFPlayersWhereOnline(boolean online);

	/**
	 * Deprecated, use {@link #isWilderness()}
	 *
	 * @return
	 */
	@Deprecated
	boolean isNone();

	/**
	 * Depreacted, use {@link #getLandRoundedInWorld(World)}
	 *
	 * @param worldName
	 * @return
	 */
	@Deprecated
	int getLandRoundedInWorld(String worldName);
		
	/**
	 * Deprecated, use {@link getMembers} 
	 */
	default Set<FPlayer> getFPlayers() {
		return this.getMembers();
	}
	
	/**
	 * Deprecated, use {@link #getFlag(Flags.OPEN)}
	 * @return
	 */
	@Deprecated
	boolean getOpen();

	/**
	 * Deprecated, use {@link #setFlag(Flags.OPEN, boolean)}
	 * @return
	 */
	@Deprecated
	void setOpen(boolean isOpen);
	
	/**
	 * Deprecated, use {@link #announcements()}
	 * @return
	 */
	@Deprecated
	Map<String, List<String>> getAnnouncements();
  
	/**
	 * Deprecated, use {@link #announcements()}
	 * @return
	 */
	@Deprecated
	void addAnnouncement(FPlayer fPlayer, String msg);
	
	/**
	 * Deprecated, use {@link #announcements()}
	 * @return
	 */
	@Deprecated
	void sendUnreadAnnouncements(FPlayer fPlayer);

	/**
	 * Deprecated, use {@link #announcements()}
	 * @return
	 */
	@Deprecated
	void removeAnnouncements(FPlayer fPlayer);


}
