package oogasalad.engine.model.setup;

import org.jooq.lambda.tuple.Tuple2;

public class Delta extends Tuple2<Integer, Integer> {

  public Delta(int v1, int v2) {
    super(v1, v2);
  }

  public Integer xdelta(){
    return v1;

  }
  public Integer ydelta(){
    return v2;
  }
}
