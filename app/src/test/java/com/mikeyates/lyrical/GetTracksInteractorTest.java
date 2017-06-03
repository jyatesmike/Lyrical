package com.mikeyates.lyrical;

import com.mikeyates.lyrical.interactors.GetTracksInteractor;
import com.mikeyates.lyrical.models.TrackModel;
import com.mikeyates.lyrical.network.iTunesSearchResponse;
import com.mikeyates.lyrical.network.iTunesService;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by jamesyates on 6/3/17.
 */

public class GetTracksInteractorTest {
    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    GetTracksInteractor interactor;

    @Mock
    iTunesService iTunesService;

    @Mock
    iTunesSearchResponse iTunesSearchResponse;

    @Test
    public void testGetTracks() throws Exception {

        when(iTunesService.getSearchResults(anyString())).thenReturn(
                Observable.just(iTunesSearchResponse)
        );

        TestSubscriber<TrackModel> subscriber = TestSubscriber.create();
        interactor.execute("metallica").subscribe();
        subscriber.assertNoErrors();
        //subscriber.assertCompleted();

        assertThat(subscriber.getOnNextEvents().isEmpty());
        //assertThat(subscriber.getOnNextEvents().get(0).getArtistName()).isEqualTo(null);

    }
}
