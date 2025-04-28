MINIKUBE :

kubectl create deployment my-nginx --image=nginx:latest
kubectl expose deployment my-nginx --port=80 --type=LoadBalancer
minikube service my-nginx 
kubectl get services
kubectl delete deployment my-nginx
kubectl get deployments
kubectl get pods


podman save --format docker-archive -o user-service.tar user-service:latest
minikube image load user-service.tar


minikube image build -t user-service:latest ./user-service

minikube image build -t notification-service:latest ./notification-service

podman build -t user-service:latest ./user-service
podman build -t notification-service:latest ./notification-service
podman save -o user-service.tar localhost/user-service:latest
podman save -o notification-service.tar localhost/notification-service:latest
minikube image load user-service.tar
minikube image load notification-service.tar




minikube ssh

kubectl apply -f C:\Users\pushpak.deore\OneDrive - Apexon\Desktop\minikube\k8sdemo
 kubectl apply -f "C:\Users\pushpak.deore\OneDrive - Apexon\Desktop\minikube\k8sdemo\notification-service" 


kubectl delete service user-service
kubectl delete service notification-service
kubectl delete deployment user-service
kubectl delete deployment notification-service


kubectl get pods 

minikube service nginx 
kubectl logs user-service-5466bd5d56-9skmr
kubectl get svc
kubectl apply -f elasticsearch/
pushpak.deore@ext.chargepoint.com



kubectl port-forward svc/user-service 8080:8080

kubectl apply -f k8sdemo/elasticsearch/
kubectl apply -f k8sdemo/kibana/
kubectl apply -f k8sdemo/logstash/
kubectl apply -f k8sdemo/mysql/
kubectl apply -f k8sdemo/rabbitmq/
kubectl apply -f k8sdemo/user-service/
kubectl apply -f k8sdemo/notification-service/
kubectl apply -f k8sdemo/nginx/

kubectl delete deployments --all
kubectl delete service --all

kubectl get pods -n elk

kubectl get daemonset


minikube start --cpus=4 --memory=7000
kubectl apply -f .\k8s_elk_filebeat\ 

RabbitMQ sepret
MySQL open
kubectl port-forward svc/user-service 8080:8080
kubectl port-forward svc/prometheus 9090:9090
kubectl port-forward svc/elasticsearch 9200:9200 

 kubectl rollout restart deployment grafana

minikube service kibana --url 
minikube service grafana --url 
inikube service user-service --url
 kubectl apply -f prometheus-config.yaml
kubectl rollout restart deployment prometheus 



 give data about pd = kubectl describe pod user-service-589df84b7-ds95w 
 podsdata with node more info = kubectl get pods -o wide
my ticket is resolve i have teleport access 
i don't have access to GitHub i have massage Kartikeyan on slack he has not seen my massage.





Kubernetes Master Components
etcd: A key-value store that holds cluster data and configuration.

Kube API Server: Central control plane component that orchestrates all cluster operations.

Kube Scheduler: Assigns pods to appropriate worker nodes based on resource availability.

Controllers: Manage various aspects of the cluster like:

Node Controller: Monitors node status.

Replication Controller: Ensures the desired number of pod replicas are running.

🔸 Kubernetes Worker Node Components
Kubelet: Agent that runs on each node, receives instructions from the API server and manages containers.

Kube Proxy: Maintains network rules and enables communication between pods and services.






