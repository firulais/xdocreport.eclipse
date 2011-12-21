package fr.opensagres.eclipse.forms.widgets;

public interface ISearcher {

	Iterable<?> search(String contents, int position);
	
}
