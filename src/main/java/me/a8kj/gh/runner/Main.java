package me.a8kj.gh.runner;

import me.a8kj.discord.webhook.DiscordEmbed;
import me.a8kj.discord.webhook.DiscordPayload;
import me.a8kj.discord.webhook.DiscordWebhookClient;
import me.a8kj.discord.webhook.WebhookSender;

public class Main {
    public static void main(String[] args) {
        final String WEBHOOK_URL = System.getenv("DISCORD_WEBHOOK_URL");

        if (WEBHOOK_URL == null || WEBHOOK_URL.isEmpty()) {
            System.err.println("Error: Missing DISCORD_WEBHOOK_URL Environment Variable");
            return;
        }

        WebhookSender sender = new DiscordWebhookClient(WEBHOOK_URL);

        DiscordPayload payload = DiscordPayload.builder()
                .username("System Monitor")
                .avatarUrl("https://i.imgur.com/NS45qcE.jpeg")
                .content("Greetings! This is a test message from the modernized Discord Webhook client.")
                .embed(DiscordEmbed.builder()
                        .title("🚀 Server Deployment Success")
                        .description("The production environment has been successfully updated to the latest build.")
                        .url("https://github.com/a8kj7sea/DiscordWebhook")
                        .color(0x32CD32)
                        .author(DiscordEmbed.Author.builder()
                                .name("a8kj7sea")
                                .iconUrl("https://i.imgur.com/NS45qcE.jpeg")
                                .build())
                        .field(DiscordEmbed.Field.builder()
                                .name("Version")
                                .value("v1.0.0")
                                .inline(true)
                                .build())
                        .field(DiscordEmbed.Field.builder()
                                .name("Status")
                                .value("Stable")
                                .inline(true)
                                .build())
                        .footer(DiscordEmbed.Footer.builder()
                                .text("Sent via Modern Discord Webhook Client")
                                .build())
                        .build())
                .build();

        try {
            sender.send(payload);
            System.out.println("Payload dispatched successfully.");
        } catch (Exception e) {
            System.err.println("Failed to send webhook: " + e.getMessage());
        }
    }
}