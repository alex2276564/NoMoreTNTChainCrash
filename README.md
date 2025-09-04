# NoMoreTNTChainCrash 🛡️💥

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.16.5+-brightgreen)](https://papermc.io/software/paper)
[![Java Version](https://img.shields.io/badge/java-17+-orange)](https://adoptium.net/installation/linux/)
[![GitHub Release](https://img.shields.io/github/v/release/alex2276564/NoMoreTNTChainCrash?color=blue)](https://github.com/alex2276564/NoMoreTNTChainCrash/releases/latest)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

**NoMoreTNTChainCrash** is a Minecraft plugin designed to prevent server crashes and lag caused by excessive TNT explosions. It achieves this by removing TNT before automated chain explosions can occur, while still allowing players to manually detonate TNT as desired.

## 📺 Problem Demonstration
[Watch the video demonstrating the issue](https://www.youtube.com/watch?v=HEet0raMR4o)

## ✨ Features

- ✅ **Crash Prevention:** Removes TNT before it can trigger automated chain explosions.
- ✅ **Manual Explosion Allowed:** Players can still ignite TNT manually.
- ✅ **Compatibility:** Fully compatible with the Ender Dragon and other major entities.
- 🔄 **Auto-Update Check:** On server start, the plugin checks for updates. If a new version is available, a notification is displayed in the console.

## 🧠 Optimization Tips for TNT Handling

If you're looking to further improve server stability and prevent TNT-related lag, here are some **important optimization tips**:

### 🔧 `max-tnt-per-tick` (in `spigot.yml`)

Many so-called "optimization guides" suggest **lowering** this value to prevent TNT lag.  
**However, this is misleading and often counterproductive.**

- Lowering this value (e.g., to 8) causes TNT to explode more slowly over many ticks, resulting in **longer but weaker lag spikes**.
- Keeping it at the default value (100) or **increasing** it to 200–300 (depending on your server's hardware) causes TNT explosions to process faster, resulting in **shorter but sharper lag**, which is usually **much better for gameplay**.

> Setting this value too low may make your server more vulnerable to TNT chain lag exploits, as it allows chain reactions to continue over many ticks.

### 🚀 `optimize-explosions: true` (in `paper.yml`)

Enable this option wherever players have access to TNT (e.g., survival, factions):

```yaml
optimize-explosions: true
```

This **significantly reduces explosion-related lag** by optimizing how blocks and entities are processed during explosions. It is safe to use and recommended by the Paper community.

> You can skip this on hubs or minigames if TNT is disabled.

---

## 📌 Note

Some budget hosting providers or control panels may recommend lowering `max-tnt-per-tick` in their "optimization guides" — not to actually improve **your** server’s performance, but to reduce CPU usage on **their** hardware.

While this might help them squeeze more clients onto a single machine, it often results in **longer and more noticeable lag** for your players, especially on TNT-heavy servers.

It's also worth noting that many similar tips found in other online guides can originate from people blindly copying advice from older or poorly-researched sources, without testing or understanding the actual effects.

🔧 **Always test and adjust settings based on your server’s real performance and your hardware capabilities.** Don’t blindly follow generic advice — especially if it’s designed for cheap or oversold hosting environments.

---

## 📥 Installation

1. **Download the latest version of the plugin from the [Releases](https://github.com/alex2276564/NoMoreTNTChainCrash/releases) section.**

2. **Place the `.jar` file into your Minecraft server’s `plugins` folder.**

3. **Restart the server** to load the plugin.

## 🛠️ Compatibility

- **Minecraft Versions:** 1.16.5 to the latest release
- **Server Software:**
    - ✅ [Paper](https://papermc.io/) (1.16.5 and newer) - **Fully Supported**
    - ✅ [Folia](https://papermc.io/software/folia) - **Fully Supported** with optimized region-aware scheduling
    - ❌ Spigot - Not supported
- **Compatible Plugins:** Fully compatible with Ender Dragon and other major Minecraft entities

## 📦 Other Plugins

Also check out my other plugins for protecting your Minecraft server:

- [**LeverLock**](https://github.com/alex2276564/LeverLock)  
  *LeverLock* - a plugin to prevent rapid lever interactions, which can cause lag or be exploited for unintended game mechanics. Works in conjunction with **AntiRedstoneClock-Remastered**, providing comprehensive protection from redstone-based lag and exploits.

- [**PermGuard**](https://github.com/alex2276564/PermGuard)  
  *PermGuard* - a plugin to enhance server security. It temporarily revokes admin permissions when a player joins the server and sending security alerts to Telegram, to prevent unauthorized access or potential security breaches. Admins can only restore permissions manually via the console using commands like `lp user playernick permission set *`.

> 🔍 **You can find more of my Minecraft plugins here:**  
> [https://github.com/alex2276564?tab=repositories](https://github.com/alex2276564?tab=repositories)

## 🆘 Support

If you encounter any issues or have suggestions for improving the module, please create an [issue](https://github.com/alex2276564/NoMoreTNTChainCrash/issues) in this repository.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

[Alex] - [https://github.com/alex2276564]

We appreciate your contribution to the project! If you like this module, please give it a star on GitHub.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/alex2276564/NoMoreTNTChainCrash/issues).

### How to Contribute

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a Pull Request.

---

Thank you for using **NoMoreTNTChainCrash**! We hope it makes your gaming experience more stable and enjoyable. 🎮🔥
