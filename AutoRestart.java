package me.DJBiokinetix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoRestart extends JavaPlugin
{
  int timeRemaining = 0;
  String restartMsg = null;
  
  public void onEnable(){
    saveDefaultConfig();
    if (!getConfig().contains("Restart.Time"))
    {
      getConfig().set("Restart.Time", Integer.valueOf(5000));
      saveConfig();
    }
    if (!getConfig().contains("Restart.Message"))
    {
      getConfig().set("Restart.Message", "&4El servidor está reiniciando y debe estar de vuelta en 5 minutos");
      saveConfig();
    }
    this.restartMsg = getConfig().getString("Restart.Message");
    this.timeRemaining = getConfig().getInt("Restart.Time");
    final String onewarn = (getConfig().getString("Restart.warn1"));
    final String twowarn = (getConfig().getString("Restart.warn2"));
    final String threewarn = (getConfig().getString("Restart.warn3"));
    final String fourwarn = (getConfig().getString("Restart.warn4"));
    final String fivewarn = (getConfig().getString("Restart.warn5"));
    final String sixwarn = (getConfig().getString("Restart.warn6"));
    final String sevenwarn = (getConfig().getString("Restart.warn7"));
    final String eightwarn = (getConfig().getString("Restart.warn8"));
    final String ninewarn = (getConfig().getString("Restart.warn9"));
    final int one = Integer.parseInt(getConfig().getString("Restart.warntime1"));
    final int two = Integer.parseInt(getConfig().getString("Restart.warntime2"));
    final int three = Integer.parseInt(getConfig().getString("Restart.warntime3"));
    final int four = Integer.parseInt(getConfig().getString("Restart.warntime4"));
    final int five = Integer.parseInt(getConfig().getString("Restart.warntime5"));
    final int six = Integer.parseInt(getConfig().getString("Restart.warntime6"));
    final int seven = Integer.parseInt(getConfig().getString("Restart.warntime7"));
    final int eight = Integer.parseInt(getConfig().getString("Restart.warntime8"));
    final int nine = Integer.parseInt(getConfig().getString("Restart.warntime9"));
    
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
    {
      public void run()
      {
        Main.this.timeRemaining -= 1;
        if (Main.this.timeRemaining == one)
        {
          Bukkit.broadcastMessage(ChatColor.GREEN + onewarn);
          Main.this.HiBeep();
        }
        if (Main.this.timeRemaining == two)
        {
          Bukkit.broadcastMessage(ChatColor.GREEN + twowarn);
          Main.this.MidBeep();
        }
        if (Main.this.timeRemaining == three)
        {
          Bukkit.broadcastMessage(ChatColor.RED + threewarn);
            Main.this.MidBeep();
        }
        if (Main.this.timeRemaining == four)
        {
          Bukkit.broadcastMessage(ChatColor.RED + fourwarn);
          Main.this.HiBeep();
        }
        if (Main.this.timeRemaining == five)
        {
          Bukkit.broadcastMessage(ChatColor.RED + fivewarn);
          Main.this.HiBeep();
        }
        if (Main.this.timeRemaining == six)
        {
          Bukkit.broadcastMessage(ChatColor.RED + sixwarn);
          Main.this.MidBeep();
        }
        if (Main.this.timeRemaining == seven)
        {
          Bukkit.broadcastMessage(ChatColor.RED + sevenwarn);
          Main.this.MidBeep1();
        }
        if (Main.this.timeRemaining == eight)
        {
          Bukkit.broadcastMessage(ChatColor.RED + eightwarn);
          Main.this.LowBeep();
        }
        if (Main.this.timeRemaining == nine)
        {
          Bukkit.broadcastMessage(ChatColor.RED + ninewarn);
          Main.this.HiBeep();
        }
        if (Main.this.timeRemaining < 1)
        {
          Bukkit.broadcastMessage(ChatColor.RED + "[Reinicio] El Servidor se a Reiniciado,Volvemos unos Segundos.");
          Main.this.Restart(Main.this.restartMsg);
        }
      }
    }, 0L, 20L);
  }
  
  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
  {
    if ((command.getName().equalsIgnoreCase("Reinicio")) || (command.getName().equalsIgnoreCase("Ar")))
    {
      if (args.length > 0)
      {
        if (sender.isOp())
        {
          if (args[0].equalsIgnoreCase("set"))
          {
            if (args.length == 3)
            {
              if (args[2].equalsIgnoreCase("H"))
              {
                this.timeRemaining = (Integer.parseInt(args[1]) * 60 * 60);
                sender.sendMessage(ChatColor.GOLD + "Reinicio establecido a " + args[1] + " Horas!");
              }
              if (args[2].equalsIgnoreCase("M"))
              {
                this.timeRemaining = (Integer.parseInt(args[1]) * 60);
                sender.sendMessage(ChatColor.GOLD + "Reinicio establecido a " + args[1] + " Minutos!");
              }
              if (args[2].equalsIgnoreCase("S"))
              {
                this.timeRemaining = Integer.parseInt(args[1]);
                sender.sendMessage(ChatColor.GOLD + "Reinicio establecido a " + args[1] + " Segundos!");
              }
              getConfig().set("Restart.Time", Integer.valueOf(this.timeRemaining));
              saveConfig();
            }
            else
            {
              sender.sendMessage(ChatColor.RED + "Uso correcto: /Reinicio set [Time] H/M/S");
              sender.sendMessage(ChatColor.GREEN + "Ejemplo: /Reinicio set 50 M");
            }
          }
          else if (args[0].equalsIgnoreCase("Ahora"))
          {
            Restart(this.restartMsg);
          }
          else if (args[0].equalsIgnoreCase("EMS"))
          {
            if (args.length > 1)
            {
              String sb = "";
              for (int i = 1; i < args.length; i++) {
                sb = sb + args[i] + " ";
              }
              getConfig().set("Restart.Message", sb);
              saveConfig();
              sender.sendMessage(ChatColor.BLUE + "Establecer el mensaje de reinicio: " + ChatColor.WHITE + sb.trim().replace("&", "§").replace("\\n", "\n"));
            }
            else
            {
              sender.sendMessage(ChatColor.RED + "Uso correcto: /Reinicio EMS [Mensaje]");
            }
          }
          else
          {
            sender.sendMessage(ChatColor.DARK_AQUA + "Ayuda:");
            sender.sendMessage(ChatColor.AQUA + "/Reinicio set [Time] [H/M/S]");
            sender.sendMessage(ChatColor.AQUA + "/Reinicio Ahora");
            sender.sendMessage(ChatColor.AQUA + "/Reinicio EMS [Mensaje]");
          }
        }
        else {
          sender.sendMessage(ChatColor.RED + "Lo siento pero no tienes permiso de ejecutar este comando!");
        }
      }
      else {
        sender.sendMessage(ChatColor.GOLD + "Reinicio en: " + ChatColor.GREEN + this.timeRemaining + " Segundos" + ChatColor.GOLD + " O " + ChatColor.DARK_AQUA + this.timeRemaining / 60 + " Minutos" + ChatColor.GOLD + " O " + ChatColor.AQUA + this.timeRemaining / 60 / 60 + " Horas" + ChatColor.GOLD + "!");
      }
      return true;
    }
    return false;
  }
  
  public void HiBeep()
  {
    for (final Player p : Bukkit.getServer().getOnlinePlayers())
    {
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 3.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 3.0F);
        }
      }, 1L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 3.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 3.0F);
        }
      }, 3L);
    }
  }
  
  public void MidBeep()
  {
    for (final Player p : Bukkit.getServer().getOnlinePlayers())
    {
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 2.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 2.0F);
        }
      }, 1L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 2.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 2.0F);
        }
      }, 3L);
    }
  }
  
  public void MidBeep1()
  {
    for (final Player p : Bukkit.getServer().getOnlinePlayers())
    {
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 1.0F);
        }
      }, 1L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 3.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 1.0F);
        }
      }, 3L);
    }
  }
  
  public void LowBeep()
  {
    for (final Player p : Bukkit.getServer().getOnlinePlayers())
    {
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 0.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 0.0F);
        }
      }, 1L);
      getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 0.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 0.0F);
        }
      }, 3L);
    }
  }
  
  public void Restart(String msg)
  {
    String message = null;
    if (msg != null)
    {
      String sb = msg;
      sb = sb.trim();
      sb = sb.replace("&", "§");
      sb = sb.replace("\\n", "\n");
      message = sb;
    }
    final String mesg = message;
    for (final Player p : Bukkit.getServer().getOnlinePlayers())
    {
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 3.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 3.0F);
        }
      }, 1L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 2.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 2.0F);
        }
      }, 4L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 2.0F);
        }
      }, 7L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 0.0F);
          p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1.0F, 0.0F);
        }
      }, 10L);
      p.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
      {
        public void run()
        {
          if (mesg != null) {
            p.kickPlayer(mesg);
          } else {
            p.kickPlayer(ChatColor.RED + "[Reinicio] El Servidor se a Reiniciado,Volvemos unos Segundos.");
          }
        }
      }, 14L);
    }
    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
    {
      public void run()
      {
        Bukkit.getServer().shutdown();
      }
    }, 20L);
  }
}
