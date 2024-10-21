import numpy as np
from sympy import centroid

data = np.array([[1,2,3],[4,5,6],[7,8,9]])

print(data)

sum = np.sum(data, axis=0) # reducing the rows 


p1= np.array([1,2])
p2 = np.array([3,4])

distance = np.sum((p1-p2)**2)

print("distance is ", np.sqrt(distance))


class KMeans:
    
    def __init__(self, n_clusters =3, max_iter=300, tol=1e-4) -> None:
        self.n_clusters=n_clusters
        self.max_iter= max_iter
        self.tol = tol
        self.centroids = None
        self.clusters = None
    
    
    def fit(X):
        num_samples, num_features = X.shape()
        #initialize centroids
        while self.max_iter:
            _compute_clusters(X)
            _update_centroids()
        
        
    def _compute_clusters(X):
        
        num_instances , dimension = X.shape
        distances = np.zeros((num_instances, self.n_clusters))  # To store the distances

        for i in num_instances:
            for j in self.n_clusters:
                distances[i,j] = np.linalg.norm(X[i], self.centroids[j])
                
        cluster_assignments = np.argmin(distances, axis=1)

           
            
        
        
    
def _update_centroids(X, cluster_assignments, K):
    """
    Update the centroids based on the new cluster assignments.
    
    Parameters:
    X : np.ndarray
        N*d array representing N data points each with d dimensions.
    cluster_assignments : np.ndarray
        N*1 array representing the cluster index assigned to each point.
    K : int
        The number of clusters (centroids).
        
    Returns:
    np.ndarray
        K*d array representing the updated centroids.
    """
    d = X.shape[1]  # Dimension of the points
    new_centroids = np.zeros((K, d))  # To store the updated centroids
    
    for k in range(K):
        # Select all points assigned to cluster k
        points_in_cluster = X[cluster_assignments == k]
        
        # If there are points assigned to this cluster, calculate the mean
        if len(points_in_cluster) > 0:
            new_centroids[k] = points_in_cluster.mean(axis=0)
        else:
            # If no points are assigned to this cluster, the centroid remains the same
            new_centroids[k] = np.zeros(d)  # Or handle it in some other way, e.g., random reinitialization

    return new_centroids

        
        
    def _initialize_centroids(X):
        random_indices = np.random.choice(X.shape[0], size=self.n_clusters, replace=False)
        
        self.centroids = X[random_indices]
    
    

