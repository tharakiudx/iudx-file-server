package iudx.file.server.database.elastic;

import org.elasticsearch.index.query.BoolQueryBuilder;
import io.vertx.core.json.JsonObject;

public interface QueryParser {

  public BoolQueryBuilder parse(BoolQueryBuilder  builder,JsonObject json);
}
