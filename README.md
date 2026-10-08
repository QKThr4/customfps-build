# Custom FPS Limit (NeoForge 1.21.1)

Adiciona o botão "Limite de FPS" (canto superior direito) no menu ESC e na tela de Opções
(que também abre pelo menu principal). Ele abre uma tela com um campo numérico: qualquer valor >= 1.

- No Minecraft, 260 ou mais = FPS ilimitado.
- O valor fica salvo em config/customfps-client.toml e é reaplicado ao abrir o jogo.
- Se você mexer no slider de FPS das Configurações de Vídeo, ele vale só até reiniciar o jogo;
  na próxima abertura, o valor deste mod volta a ser aplicado.
- Para voltar ao normal, coloque fpsLimit = 0 no .toml (ou apague o arquivo).

Compilação: copie a pasta src/ para o MDK oficial 1.21.1 (mod id: customfps) e rode ./gradlew build.
