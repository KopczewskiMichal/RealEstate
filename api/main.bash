kubectl apply -f main.yaml
kubectl port-forward deployment/backend-app 3000:3000
