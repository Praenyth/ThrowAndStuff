package nyth.prae.awesome.plugins.throwandstuff.listener;

import nyth.prae.awesome.plugins.throwandstuff.ThrowAndStuff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerClickListener implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        if (event.getHand().equals(EquipmentSlot.HAND)) {
            if (event.getRightClicked() instanceof Player) {

                Player target = (Player) event.getRightClicked();
                if (player.isSneaking()) {

                    if (target.getVehicle() != null) {

                        if (!ThrowAndStuff.cooldownOn.contains(player.getUniqueId())) {
                            target.getVehicle().eject();
                            target.setVelocity(player.getLocation().getDirection().multiply(2));
                            return;
                        }

                    }

                    player.addPassenger(target);
                    Bukkit.getScheduler().runTaskLater(ThrowAndStuff.instance, () -> {

                        if (ThrowAndStuff.cooldownOn.contains(player.getUniqueId())) {

                            ThrowAndStuff.cooldownOn.remove(player.getUniqueId());

                        }

                    }, 20);

                }

            }
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        if (ThrowAndStuff.cooldownOn.contains(player.getUniqueId())) {

            ThrowAndStuff.cooldownOn.remove(player.getUniqueId());

        }

    }

}
