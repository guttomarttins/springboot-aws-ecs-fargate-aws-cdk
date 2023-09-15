# springboot-aws-ecs-fargate-aws-cdk
- Amazon VPC
- Amazon ECS
- Application Load Balancer
- AWS Fargate
- AWS SNS
- AWS RDS
- AWS CDK

cdk list
--lista todas as stacks criadas

cdk diff
--mostra todas as atualizações realizadas

cdk deploy
# cdk deploy --parameters Rds:databasePassword=123vaifilhao Rds Vpc Cluster Sns Service01 Service01 Ddb
--enviar/atualizar alteracoes

cdk destroy
--deletar todas as tasks criadas
