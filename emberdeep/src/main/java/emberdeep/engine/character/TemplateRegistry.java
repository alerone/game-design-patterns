package emberdeep.engine.character;

import java.util.EnumMap;
import java.util.Map;

/**
 * TemplateRegistry
 */
public class TemplateRegistry {

    private final Map<EnemyKind, EnemyTemplate> templates = 
        new EnumMap<>(EnemyKind.class);

    public void register(EnemyTemplate template) {
        templates.put(template.kind(), template);
    }

    public EnemyTemplate get(EnemyKind kind) {
        EnemyTemplate template = templates.get(kind);
        if (template == null)
            throw new IllegalArgumentException("No hay plantilla para " + kind);
        return template;
    }
}
