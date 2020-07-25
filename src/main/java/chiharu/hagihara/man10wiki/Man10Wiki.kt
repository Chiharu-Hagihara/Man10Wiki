package chiharu.hagihara.man10wiki

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Man10Wiki : JavaPlugin() {

    var wikiurl: String = "§c§lSetting: /wiki edit <URL HERE>"
    var config = YamlConfiguration()

    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        loadConfig()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p = sender as Player

        if (sender is ConsoleCommandSender) {
            sender.sendMessage("This command cannot be executed from the console.")
            return false
        }

        if (args.isEmpty()) {
            showURL(p)
            return true
        }

        val cmd = args[0]

        if (cmd == "help") {
            showHelp(p)
            return true
        }

        if (cmd == "reload") {
            if (p.hasPermission("wiki.op")) {
                reloadConfig()
                p.sendMessage("§bMan10Wiki Config reloaded.")
            } else {
                p.sendMessage("§cThis command can only be executed by a player with <wiki.op> privileges.")
                return false
            }
        }
        return true
    }

    fun showHelp(p: Player) {
        p.sendMessage("§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§b§lMan10Wiki§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=")
        p.sendMessage("/wiki WikiのURLを見ることができます。")
        if (p.hasPermission("wiki.op")) {
            p.sendMessage("/wiki reload configをreloadします。")
        }
        p.sendMessage("§7§lCreated by Mr_El_Capitan")
        p.sendMessage("§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=")
    }

    fun showURL(p: Player){
        p.sendMessage("§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§b§lMan10Wiki§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=")
        p.sendMessage("§lWikiURL: §n$wikiurl")
        p.sendMessage("§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=§f§l=§7§l=")
    }

    fun loadConfig() {
        try {
            wikiurl = config.getBoolean("wiki").toString()
        } catch (e: NullPointerException) {
            logger.info("値の取得に失敗")
            e.printStackTrace()
        }

    }
}