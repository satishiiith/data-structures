import random
from xmlrpc.client import boolean
import numpy as np
from typing import Dict
import copy

class KMeans:
    def __init__(self, k:int, max_iter=300, tol=0.001) -> None:
        self.k = k
        self.centroids:np.ndarray = None
        self.max_iter = max_iter
        self.tol = tol
    
    def fit(self, data:np.ndarray):
        self._initialize_centroids(data)
        
        for i in range(self.max_iter):
          clusters = self._assign_clusters(data)
          old_centroids = copy.deepcopy(self.centroids)
          self._update_centroids(clusters,data)
          if  self._isconverged(old_centroids):
              print("converged")
              break
        
        
    def _initialize_centroids(self, data:np.ndarray):
        num_samples = data.shape[0]
        indicies = np.random.choice(num_samples, size=self.k, replace=False)
        self.centroids = data[indicies]
        
        
    def _assign_clusters(self, data:np.ndarray)-> list[ list]:
        # calculate distance between data and centroids . Centroid is ndarray for size K*n_dims
        # return k cluster, each cluster have list of indices in data
        data = data[:,np.newaxis] 
        distances =np.linalg.norm(data-self.centroids, axis=2) # num_samples*k*n_dimensions
        pointwise_centroids = np.argmin(distances,axis=1)   # [1 ,2, 1 ,2]  
        clusters = [[] for _ in range(self.k)]
        for idx , centroid in enumerate(pointwise_centroids):
            clusters[centroid].append(idx)
        return clusters
        
    
    def _update_centroids(self,clusters,data):
        for idx,cluster in enumerate(clusters):
            points_cluster = data[cluster]
            if len(points_cluster) > 0:  # Avoid division by zero if cluster is empty
                new_centroid = np.mean(points_cluster, axis=0)
                print("new centroid",new_centroid )
                self.centroids[idx] = new_centroid
    
    
    def _isconverged(self,old_centroids)->bool:
        diff = np.linalg.norm(self.centroids-old_centroids, axis=1)
        print("differenec  is ", diff)
        if np.all(diff<self.tol):
            return True
        
    def predict(self, data: np.ndarray) -> np.ndarray:
        # Return the index of the nearest centroid for each data point
        distances = np.linalg.norm(data[:, np.newaxis] - self.centroids, axis=2)
        return np.argmin(distances, axis=1)
        
        
if __name__ == "__main__":
    # Create sample data (e.g., 300 points clustered around 3 centers)
    from sklearn.datasets import make_blobs
    data, true_labels = make_blobs(n_samples=300, centers=3, n_features=2, random_state=42)

    # Initialize and fit the KMeans model
    kmeans = KMeans(k=3)
    kmeans.fit(data)
    predicted_labels = kmeans.predict(data)
    import matplotlib.pyplot as plt
    plt.scatter(data[:, 0], data[:, 1], c=predicted_labels, cmap='viridis', marker='o', label="Data Points")
    plt.scatter(kmeans.centroids[:, 0], kmeans.centroids[:, 1], s=300, c='red', marker='x', label="Centroids")
    plt.legend()
    plt.show()


    # Predict cluster assignments for the data
   # predicted_labels = kmeans.predict(data)
        
    