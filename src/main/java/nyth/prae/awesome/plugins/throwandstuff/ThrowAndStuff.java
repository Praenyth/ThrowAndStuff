package nyth.prae.awesome.plugins.throwandstuff;

import nyth.prae.awesome.plugins.throwandstuff.listener.PlayerClickListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public final class ThrowAndStuff extends JavaPlugin {

    public static ThrowAndStuff instance;
    public static List<UUID> cooldownOn = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerClickListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
