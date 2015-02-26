package creare.sc.util;

import java.util.List;

import play.libs.F;


public class OptionUtil {

	public static <T> F.Option<T> apply(T value) {
		if(value != null) {
			return F.Option.Some(value);
		} else {
			return F.Option.None();
		}
	}

	public static <T> F.Option<List<T>> apply(List<T> value) {
		if(value != null && value.size() != 0) {
			return F.Option.Some(value);
		} else {
			return F.Option.None();
		}
	}

//	public static <String> F.Option<String> applyWithString(String value) {
//		if(value != null && !value.equals("")) {
//			return F.Option.Some(value);
//		} else {
//			return F.Option.None();
//		}
//	}
//
//	public static <T> F.None<T> none() {
//		return new F.None<T>();
//	}
//
//	public static <T> Option<T> asScala(F.Option<T> value) {
//		if(value.isDefined()) {
//			return Option$.MODULE$.apply(value.get());
//		} else {
//			return Option$.MODULE$.empty();
//		}
//	}
}
