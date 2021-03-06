package pl.allegro.tech.search.elasticsearch.tools.reindex.query.filter;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BaseQueryBuilder;
import pl.allegro.tech.search.elasticsearch.tools.reindex.query.BoundedSegment;
import pl.allegro.tech.search.elasticsearch.tools.reindex.query.PrefixSegment;
import pl.allegro.tech.search.elasticsearch.tools.reindex.query.RangeSegment;

import java.util.List;

public class BoundedFilterFactory {

  private final ImmutableMap<Class<? extends BoundedSegment>, BoundedFilterCreationStrategy> strategy =
      new ImmutableMap.Builder<Class<? extends BoundedSegment>, BoundedFilterCreationStrategy>()
          .put(PrefixSegment.class, new PrefixFilterCreationStrategy())
          .put(RangeSegment.class, new RangeFilterCreationStrategy())
          .build();

  public BaseQueryBuilder createBoundedFilter(String fieldName, BoundedSegment boundedSegment) {
    return strategy.get(boundedSegment.getClass()).create(fieldName, boundedSegment);
  }

}
