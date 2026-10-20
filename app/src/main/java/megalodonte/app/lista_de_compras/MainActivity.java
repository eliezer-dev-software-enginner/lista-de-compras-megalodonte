package megalodonte.app.lista_de_compras;
import java.nio.charset.StandardCharsets;
import megalodonte.android.bridge.AndroidJsoncScreen;
import megalodonte.android.bridge.MegalodonteActivity;
import megalodonte.application.MegalodonteApp;
import megalodonte.base.theme.ThemeManager;
import megalodonte.theme.DefaultTheme;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public final class MainActivity extends MegalodonteActivity {
    private JSONArray items;
    private AndroidJsoncScreen screen;
    @Override protected void main() {
        try (var stream = getAssets().open("views/home.view.jsonc")) {
            ThemeManager.setTheme(new DefaultTheme());
            items = new JSONArray(getPreferences(0).getString("items", "[]"));
            screen = new AndroidJsoncScreen(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
            screen.onAction(this::act);
            refresh("");
            MegalodonteApp.run(context -> context.useView(screen));
        } catch (java.io.IOException | JSONException error) { throw new IllegalStateException(error); }
    }
    private void act(String action) {
        try {
        String message;
        switch (action) {
            case "add" -> {
                String name = screen.getStringState("item").trim();
                if (name.isEmpty()) { refresh("Digite um item antes de adicionar."); return; }
                items.put(new JSONObject().put("name", name).put("done", false));
                screen.setState("item", "");
                message = "Item adicionado.";
            }
            case "toggle", "remove" -> {
                int index;
                try { index = Integer.parseInt(screen.getStringState("number").trim()) - 1; }
                catch (NumberFormatException error) { refresh("Informe o número do item."); return; }
                if (index < 0 || index >= items.length()) { refresh("Item não encontrado."); return; }
                if (action.equals("remove")) { items.remove(index); message = "Item removido."; }
                else {
                    JSONObject item = items.getJSONObject(index);
                    item.put("done", !item.optBoolean("done"));
                    message = "Item atualizado.";
                }
            }
            default -> throw new IllegalArgumentException(action);
        }
        getPreferences(0).edit().putString("items", items.toString()).apply();
        refresh(message);
        } catch (JSONException error) { throw new IllegalStateException(error); }
    }
    private void refresh(String message) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            text.append(i + 1).append(". ").append(item.optBoolean("done") ? "☑ " : "☐ ")
                    .append(item.optString("name")).append('\n');
        }
        screen.setState("items", text.isEmpty() ? "Nenhum item ainda." : text.toString());
        screen.setState("status", message);
    }
}
