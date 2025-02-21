/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ac.simons.neo4j.migrations.core.refactorings;

import java.util.Optional;

/**
 * Abstract base class to hold state that many refactorings have, such as custom queries or a batchsize.
 *
 * @author Michael J. Simons
 * @soundtrack Black Sabbath - Black Sabbath
 * @since 1.10.0
 */
abstract class AbstractCustomizableRefactoring {

	/**
	 * An optional, custom query to generate the list of nodes or relationships whose labels or types should be renamed.
	 * This query must return rows with one single element per row. This is checked upon before executing.
	 */
	protected final String customQuery;

	/**
	 * The batch size to perform renaming. If {@literal null}, no batching is attempted and the refactoring will use one
	 * a transactional function when applied. Setting this to a value different from {@literal null} also requires Neo4j >= 4.4
	 */
	protected final Integer batchSize;

	protected AbstractCustomizableRefactoring(String customQuery, Integer batchSize) {
		this.customQuery = customQuery;
		this.batchSize = batchSize;
	}

	protected final String filterCustomQuery(String newCustomQuery) {
		return Optional.ofNullable(newCustomQuery).map(String::trim)
			.filter(s -> !s.isEmpty())
			.orElse(null);
	}
}
