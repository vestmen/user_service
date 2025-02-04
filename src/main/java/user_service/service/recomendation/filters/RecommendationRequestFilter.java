package user_service.service.recomendation.filters;

import user_service.dto.recomendation.FilterRecommendationRequestsDto;
import user_service.entity.recommendation.RecommendationRequest;

import java.util.stream.Stream;

public interface RecommendationRequestFilter {
    boolean isApplicable(FilterRecommendationRequestsDto filter);

    Stream<RecommendationRequest> apply(
            Stream<RecommendationRequest> requestStream,
            FilterRecommendationRequestsDto filter
    );
}
