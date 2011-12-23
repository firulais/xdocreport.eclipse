package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;

public class SearchControlObservables {

	public static IObservableValue observeModel(Realm realm,
			SearchControl control) {
		return SearchControlProperties.model().observe(realm, control);
	}

	public static IObservableValue observeModel(SearchControl control) {
		return SearchControlProperties.model().observe(control);
	}
}
