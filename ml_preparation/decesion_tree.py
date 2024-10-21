class DecisionTreeNode:
    """A decision tree node."""
    def __init__(self, feature_index=None, threshold=None, left=None, right=None, *, value=None):
        self.feature_index = feature_index
        self.threshold = threshold
        self.left = left
        self.right = right
        self.value = value

class DecisionTreeClassifier:
    def __init__(self, max_depth=None):
        self.max_depth = max_depth
        self.root = None

    def fit(self, X, y):
        """Fit the decision tree classifier."""
        self.root = self._build_tree(X, y)

    def _build_tree(self, X, y, depth=0):
        """Builds the tree recursively."""
        num_samples, num_features = X.shape
        if num_samples == 0 or depth == self.max_depth:
            return None
        
        # Helper method to calculate the best split
        best_split = self._get_best_split(X, y, num_features)
        if not best_split:
            return None
        
        left_subtree = self._build_tree(*self._split_dataset(X, y, *best_split['indices_left']), depth+1)
        right_subtree = self._build_tree(*self._split_dataset(X, y, *best_split['indices_right']), depth+1)
        
        return DecisionTreeNode(best_split['feature_index'], best_split['threshold'], left_subtree, right_subtree)

    def predict(self, X):
        """Make predictions."""
        return [self._predict(inputs, self.root) for inputs in X]

    def _predict(self, inputs, node):
        """Predict a single sample using the tree."""
        if node is None or node.value is not None:
            return node.value
        if inputs[node.feature_index] < node.threshold:
            return self._predict(inputs, node.left)
        else:
            return self._predict(inputs, node.right)

    def _get_best_split(self, X, y, num_features):
        """Find the best feature and threshold to split on."""

        # This should calculate and return the best split based on a criterion like Gini index
        pass

    def _split_dataset(self, X, y, feature_index, threshold):
        """Split the dataset."""
        indices = X.index
        right_mask = indices[X.iloc[:, feature_index]<=threshold]
        left_mask = indices[X.iloc[:, feature_index]>threshold]
        X_right = X.iloc[right_mask]
        Y_right = y.iloc[right_mask]
        
        

        
        # This should split the dataset into left and right based on the feature index and threshold
        pass

# Usage
# X and y are your features and target variable arrays respectively
# dt = DecisionTreeClassifier(max_depth=3)
# dt.fit(X, y)
# predictions = dt.predict(X)
