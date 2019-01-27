package net.hotsmc.sg.menu;

import net.hotsmc.core.gui.menu.Button;
import net.hotsmc.core.gui.menu.Menu;
import net.hotsmc.core.other.Style;
import net.hotsmc.sg.HSG;
import net.hotsmc.sg.player.GamePlayer;
import net.hotsmc.sg.map.VoteMap;
import net.hotsmc.sg.utility.ItemUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class VoteMenu extends Menu {

    public VoteMenu() {
        super(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Vote a map";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        if(HSG.getGameTask().getVoteManager().isVoting()){
            for(int i = 0; i < HSG.getGameTask().getVoteManager().getVoteMaps().size(); i++){
                VoteMap voteMap = HSG.getGameTask().getVoteManager().getVoteMaps().get(i);
                buttons.put(i, new Button() {
                    @Override
                    public ItemStack getButtonItem(Player player) {
                        return ItemUtility.createItemStack(Style.AQUA + voteMap.getVoteID() + ". " + voteMap.getMapName() + ": " + Style.WHITE + voteMap.getVotes(), Material.EMPTY_MAP, false);
                    }
                    @Override
                    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
                        player.closeInventory();
                        GamePlayer gamePlayer = HSG.getGameTask().getGamePlayer(player);
                        if(gamePlayer == null)return;
                        HSG.getGameTask().getVoteManager().addVote(gamePlayer, voteMap.getVoteID());
                    }
                });
            }
        }
        return buttons;
    }
}
