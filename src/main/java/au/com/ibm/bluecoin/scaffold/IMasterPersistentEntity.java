package au.com.ibm.bluecoin.scaffold;

import java.io.Serializable;

public interface IMasterPersistentEntity<K> extends Serializable {
	String getSearchResultInfo();
	K getId();
}
