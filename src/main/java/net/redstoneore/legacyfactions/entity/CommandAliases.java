package net.redstoneore.legacyfactions.entity;

import com.google.common.collect.Lists;

import net.redstoneore.legacyfactions.entity.persist.Persist;

import java.util.List;



public class CommandAliases {

	// -------------------------------------------------- //
	// COMMAND ALIASES
	// -------------------------------------------------- //

	// Base command for Factions 'f' is default, you can add more or changed it.
	public static List<String> baseCommandAliases = Lists.newArrayList("f", "나라");

	// Customise any alias for all commands
	public static List<String> cmdAliasesAdmin = Lists.newArrayList("leader", "리더변경");
	public static List<String> cmdAliasesAnnounce = Lists.newArrayList("announce", "ann"); //no perm
	public static List<String> cmdAliasesAutoclaim = Lists.newArrayList("autoclaim", "자동점유");
	public static List<String> cmdAliasesAutohelp = Lists.newArrayList("?", "help", "도움말");
	public static List<String> cmdAliasesBoom = Lists.newArrayList( "explosions", "폭발설정");  //no perm
	public static List<String> cmdAliasesBypass = Lists.newArrayList("bypass"); // no perm
	public static List<String> cmdAliasesChat = Lists.newArrayList("chat", "채팅");
	public static List<String> cmdAliasesChatspy = Lists.newArrayList("chatspy"); //no perm
	public static List<String> cmdAliasesClaim = Lists.newArrayList("claim", "점유");
	public static List<String> cmdAliasesClaimLine = Lists.newArrayList("claimline", "직선점유");
	public static List<String> cmdAliasesColeader = Lists.newArrayList("coleader", "setcoleader", "공동리더");
	public static List<String> cmdAliasesConfig = Lists.newArrayList("config"); //no perm
	public static List<String> cmdAliasesConvert = Lists.newArrayList("convert"); //no perm
	public static List<String> cmdAliasesDebug = Lists.newArrayList("debug");  //no perm
	public static List<String> cmdAliasesCreate = Lists.newArrayList("create", "생성");
	public static List<String> cmdAliasesDeinvite = Lists.newArrayList("deinvite", "초대취소");
	public static List<String> cmdAliasesDelwarp = Lists.newArrayList("delwarp", "deletewarp", "dw");   //no perm
	public static List<String> cmdAliasesDescription = Lists.newArrayList( "description", "설명변경");
	public static List<String> cmdAliasesDisband = Lists.newArrayList("delete", "삭제");
	public static List<String> cmdAliasesFlag = Lists.newArrayList("flag", "f");   //no perm
	public static List<String> cmdAliasesFlagSet = Lists.newArrayList("set", "s");  //no perm
	public static List<String> cmdAliasesFlagList = Lists.newArrayList("list", "목록");
	public static List<String> cmdAliasesHelp = Lists.newArrayList("help", "도움말");
	public static List<String> cmdAliasesHome = Lists.newArrayList("home", "홈");
	public static List<String> cmdAliasesInvite = Lists.newArrayList("invite", "초대");
	public static List<String> cmdAliasesJoin = Lists.newArrayList("join", "가입");
	public static List<String> cmdAliasesKick = Lists.newArrayList("kick", "추방");
	public static List<String> cmdAliasesBan = Lists.newArrayList("ban"); //no perm
	public static List<String> cmdAliasesLeave = Lists.newArrayList("leave", "탈퇴", "나가기");
	public static List<String> cmdAliasesList = Lists.newArrayList("list", "목록");
	public static List<String> cmdAliasesLock = Lists.newArrayList("lock"); //no perm
	public static List<String> cmdAliasesLogins = Lists.newArrayList("loginnofity", "접속알림");
	public static List<String> cmdAliasesMap = Lists.newArrayList("map", "지도");
	public static List<String> cmdAliasesMod = Lists.newArrayList("officer", "setofficer", "관리자");
	public static List<String> cmdAliasesModifyPower = Lists.newArrayList("modifypower", "modpower", "mp", "pm"); //no perm
	public static List<String> cmdAliasesMoney = Lists.newArrayList("money", "돈"); //no perm
	public static List<String> cmdAliasesMoneyBalance = Lists.newArrayList("balance", "돈"); //no perm
	public static List<String> cmdAliasesMoneyDeposit = Lists.newArrayList("deposit", "d"); //no perm
	public static List<String> cmdAliasesMoneyTransferFf = Lists.newArrayList("ff"); //no perm
	public static List<String> cmdAliasesMoneyTransferFp = Lists.newArrayList("fp"); //no perm
	public static List<String> cmdAliasesMoneyTransferPf = Lists.newArrayList("pf"); //no perm
	public static List<String> cmdAliasesMoneyWithdraw = Lists.newArrayList("withdraw", "w"); //no perm
	public static List<String> cmdAliasesOpen = Lists.newArrayList("open", "개방");
	public static List<String> cmdAliasesOwner = Lists.newArrayList("owner","청크부여");
	public static List<String> cmdAliasesOwnerList = Lists.newArrayList("ownerlist", "청크주인");
	public static List<String> cmdAliasesPeaceful = Lists.newArrayList("peaceful"); //no perm
	public static List<String> cmdAliasesPermanent = Lists.newArrayList("permanent"); //no perm
	public static List<String> cmdAliasesPermanentPower = Lists.newArrayList("permanentpower"); //no perm
	public static List<String> cmdAliasesPower = Lists.newArrayList("power", "파워", "파워확인");
	public static List<String> cmdAliasesPowerBoost = Lists.newArrayList("powerboost"); //no perm
	public static List<String> cmdAliasesRelationAlly = Lists.newArrayList("ally", "동맹", "동맹국");
	public static List<String> cmdAliasesRelationTruce = Lists.newArrayList("truce","휴전", "휴전국");
	public static List<String> cmdAliasesRelationEnemy = Lists.newArrayList("enemy", "적군", "적국");
	public static List<String> cmdAliasesRelationNeutral = Lists.newArrayList("neutral", "중립", "중립국");
	public static List<String> cmdAliasesReload = Lists.newArrayList("reload"); //no perm
	public static List<String> cmdAliasesSafeunclaimall = Lists.newArrayList("safeunclaimall"); //no perm
	public static List<String> cmdAliasesSaveAll = Lists.newArrayList("saveall", "save"); //no perm
	public static List<String> cmdAliasesScoreboard = Lists.newArrayList("scoreboard", "스코어보드");
	public static List<String> cmdAliasesSeeChunk = Lists.newArrayList("seechunk", "청크보기", "경계보기");
	public static List<String> cmdAliasesSethome = Lists.newArrayList("sethome", "홈설정", "셋홈");
	public static List<String> cmdAliasesSetwarp = Lists.newArrayList("setwarp"); //no perm
	public static List<String> cmdAliasesShow = Lists.newArrayList("info", "정보");
	public static List<String> cmdAliasesShowInvites = Lists.newArrayList("showinvites", "초대목록");
	public static List<String> cmdAliasesStatus = Lists.newArrayList("status", "s"); //no perm
	public static List<String> cmdAliasesStuck = Lists.newArrayList("escape", "탈출");
	public static List<String> cmdAliasesTag = Lists.newArrayList("rename", "이름변경");
	public static List<String> cmdAliasesTitle = Lists.newArrayList("title", "칭호");
	public static List<String> cmdAliasesToggleAllianceChat = Lists.newArrayList("채팅차단");
	public static List<String> cmdAliasesTop = Lists.newArrayList("top", "순위");
	public static List<String> cmdAliasesUnban = Lists.newArrayList("unban"); //no perm
	public static List<String> cmdAliasesUnclaim = Lists.newArrayList("unclaim", "점유해제");
	public static List<String> cmdAliasesUnclaimAll = Lists.newArrayList("unclaimall", "점유모두해제");
	public static List<String> cmdAliasesVersion = Lists.newArrayList("version","버전정보");
	public static List<String> cmdAliasesWarp = Lists.newArrayList("warp", "warps"); //no perm
	public static List<String> cmdAliasesWarunclaimall = Lists.newArrayList("warunclaimall"); //no perm
	public static List<String> cmdAliasesStyle = Lists.newArrayList("style"); //no perm

	// -------------------------------------------- //
	// Persistance
	// -------------------------------------------- //

	private static transient CommandAliases i = new CommandAliases();

	public static void load() {
		Persist.get().loadOrSaveDefault(i, CommandAliases.class, "commandAliases");
	}

	public static void save() {
		Persist.get().save(i);
	}
}


