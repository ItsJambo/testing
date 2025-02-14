package com.itsjambo.thunderspacebetterpotions.commands;

import com.itsjambo.thunderspacebetterpotions.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("tsbp-help")) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] You do not have permission to use this command!"));
            return true;
        }

        sender.sendMessage(ColorUtils.parse("&#DCFB08&lT&#DDF808&lh&#DEF507&lu&#E0F207&ln&#E1EF07&ld&#E2EC07&le&#E3E906&lr&#E4E606&lS&#E6E306&lp&#E7E006&la&#E8DD05&lc&#E9DA05&le&#EAD705&lB&#ECD404&le&#EDD104&lt&#EECD04&lt&#EFCA04&le&#F1C703&lr&#F2C403&lP&#F3C103&lo&#F4BE02&lt&#F5BB02&li&#F7B802&lo&#F8B502&ln&#F9B201&ls &#FBAC01&lH&#FDA901&le&#FEA600&ll&#FFA300&lp"));
        sender.sendMessage(ColorUtils.parse("&#DCFB08&l| &f/tsbp-create [name] [description] [effect] [level] [duration] - &#DCFB08c&#DEF507r&#E1EF07e&#E3E906a&#E5E406t&#E8DE05e &#ECD204a &#F1C603p&#F3C003o&#F6BA02t&#F8B502i&#FAAF01o&#FDA901n&#FFA300;"));
        sender.sendMessage(ColorUtils.parse("&#DCFB08&l| &f/tsbp-help - &#DCFB08s&#DFF507h&#E1EE07o&#E4E806w &#E9DC05t&#EBD505h&#EECF04i&#F0C903s &#F5BC02m&#F8B602e&#FAB001n&#FDA901u&#FFA300;"));
        sender.sendMessage(ColorUtils.parse("&#DCFB08&l| &f/tsbp-id [id] - &#DCFB08i&#DDF808s&#DFF507s&#E0F207u&#E1EE07a&#E2EB07n&#E4E806c&#E5E506e &#E7DF05o&#E9DC05f &#EBD505t&#ECD204h&#EECF04e &#F0C903p&#F1C603o&#F3C203t&#F4BF03i&#F5BC02o&#F6B902n &#F9B301b&#FAB001y &#FDA901I&#FEA600D&#FFA300;"));
        sender.sendMessage(ColorUtils.parse("&#DCFB08&l| &f/tsbp-reload - &#DCFB08r&#DEF608e&#E0F107l&#E2EB07o&#E4E606a&#E6E106d &#EAD705t&#ECD204h&#EFCC04e &#F3C203p&#F5BD02l&#F7B802u&#F9B301g&#FBAD01i&#FDA800n&#FFA300;"));
        sender.sendMessage(ColorUtils.parse("&#DCFB08&l| &f/tsbp-addeffect [potionID] [effect] [level] [duration] - &#DCFB08a&#DDF808d&#DEF507d &#E1F007a&#E2ED07n &#E4E706e&#E5E406f&#E6E106f&#E7DF05e&#E8DC05c&#EAD905t &#ECD304t&#EDD004o &#EFCB04a &#F1C503p&#F3C203o&#F4BF03t&#F5BD02i&#F6BA02o&#F7B702n &#F9B101b&#FAAE01y &#FDA901I&#FEA600D&#FFA300."));
        return true;
    }


}