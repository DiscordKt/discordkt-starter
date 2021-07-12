<a href="https://discord.gg/REZVVjA">
    <img alt="Discord Banner" src="https://discordapp.com/api/guilds/453208597082406912/widget.png?style=banner2"/>
</a><br/>
<a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/Kotlin-1.5.20-blue.svg?logo=Kotlin" alt="Kotlin 1.5.20"></a>
<a href="https://github.com/JakeJMattson/DiscordKt">
<img alt="DiscordKt 0.22.0-SNAPSHOT" src="https://img.shields.io/badge/DiscordKt-0.22.0-blue.svg?logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABmJLR0QA/wD/AP+gvaeTAAAEvElEQVRYw+2Yy2seVRiHn3Ob756kiWlrq7ZqDSJUsSqoK4srQXHjRnShf4ILl/4FrkVcKLgS1FW3FRR05b1IMVXbNG2SJvny5ct3nZkz5+IitpW2giYOFskLA7M4Z+Y57/m9P857YC92F+Lqy/T0M/F2Atvc/EIAyNs9g3uAuw3974lZMFc9jsJwLj+Dj+72ATxSO8KrB1/nHjOHd5q1rM3Hm+/zc/b9f1vFTV3npbuf49k7ThJ9gisM3ulrz5nBj3zSfY+u39hxFe8IUCA4efgRXjv2PA05iXeaiGHyiRa2L+l87SnsNmRaBE53T3F68CnuH2z7jgHvm5rljRPPMTdxz7VMVeemOPTyYSp3JgCMzlt+fWdA71zEO41zmqV0hY823mPBzpcHeKg1wYcvvEJCDe8V1GpMv3iM5qMzN42NEdpfjFn4YETakXinyQt4e/ktLtvz5Rj1s/cfpVWPmEpO88Emh988cUs4ACFg/8k6j707w+wT23NqVc+Tk0+X54ONisBUcnRiqT9/P157vPd/OT7GiFM5B16SJJWcpJJTM6o8m6lVBCaxSBmIaU5sVXGFxXuB1gYpr/48YnNLlmYgInISTDVDFoZ6mYB3tAw6sUjlQVyXbAyRwlqUUiAEaZoSQ7imcCEiSSXHK89krVoeoNEek1i8V3hxc01577F5QSTcIMhtDUrlSYwrD3CyLrcz6BUBzy3LPsuham7gu57BZjUpr0jqFYE2BdoUmIX5bS+5ack3fDKC+2ENnVhMJadqYnkZbA9ztCkIMiCWziJ7bewjTxFmD17nUX8CXB/gv7pAuDTCJBrvFZ1sUB5gp+9Z35LMTjm0CsjxKuarUxQH7iV/+ElivQFCIEYp6odzhF9WiU5jEo2QnvZajc1RiRo0JtIbw3CkmZkKTLX+yGb7POqzRYqDx1AuIi5dIRSCkGikDPQtLK9VGI8jifHlAQ5zi1YFISjWupLeSLN/2lE1AekDanWe4BVBK7xQZCGy1jV0uooQLcoERn5YogZHGd3UMtXQaKmwXnLpimKiEZmddmgp8TIQRaDT1bTbGlsEjLE4qehmlp4dlQfoY6CfFgxTz76mpllVSKkYZprhkqHViOAl3X5CnguEdhgiQ+9pDwNpIfEyLw/w4sYYj0NIxcYwMBgrZiY0iY5EL+mPFdFHoijQJuKjY3Mo2RpFolBECYuD1fJ8sJ85Pp/f4EpvjFKBAsdyz9IeWKJyaO1QxiGVo59nLG9ljIscpR1bdsB36xdJfVZmkXhSF/lmsc++dsrxuxrsq1VIi8jlTqBZUcQg6I8FtpAorcl9wfzGgKWtETEKBkVa4hZ3Uu6cSjg6U6U7Lvjy1x5376vw0KEGiTQMck8MEoREKMFCZ8C59SHOgZSSy70Ri71u+U3T/omEx4+2mKprQJBIwYOHGhyZrkFULG9Zzi6NGeWeGCVjG/hppcul7t+3mF01TVcnHp2t8tiRFonePuNJISAIfIQYBD4IfmuP+Gmlh/NhR13djvviCCy0M1a6OcfvavLAgfr2IUtEBLDSt3y32GeY766B33XjnrvItxcH/LaeMXewipaCC+2M1Z69va4+tsYFX18o9m639gD/d4B7sdv4Hc8YdmfiizvVAAAAAElFTkSuQmCC">
</a>
<a href="https://hub.docker.com/repository/docker/theprogrammershangout/judgebot/tags?page=1">
<img src="https://img.shields.io/docker/cloud/build/theprogrammershangout/judgebot.svg?label=Docker&logo=docker" alt="Docker">
</a>

# DiscordKt Starter

> A template repo to get up and running with DiscordKt.

# Template Structure
This repo gives you a fully working, and extentable starter bot that contains the following:
- Multi-Guild configuration.
- A prebuilt `setup` command that goes through a conversation to create a guild configuration.
- Prebuilt commands to change those configuration vales, and show how to add more if needed.
- Main.kt with mention embed, command permissions and basic configration already setup.  
- A Permissions Service, to allow commands or events to be restricted to roles. Admin or Staff roles can be setup using the `setup` command.
- Complete Docker configuration to build the bot.
- Docker-Compose setup to allow the bot to be run easily.

# Setup
This bot can be setup and run locally using Docker and Docker-Compose. To start it locally, do the following:
```
$ cp .env.example .env
```
Edit the .env file with your favourite editor, filling out the token, default prefix and mongo configuration.

```
$ docker-compose up --detach
```

## Author
👤 **Ddivad**
* Github: [@ddivad195](https://github.com/ddivad195)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page](https://github.com/the-programmers-hangout/JudgeBot/issues).

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2020 [The Programmers Hangout](https://github.com/the-programmers-hangout).<br />
This project is [MIT](https://github.com/ddivad195/discordkt-starter/blob/master/LICENSE) licensed.

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_