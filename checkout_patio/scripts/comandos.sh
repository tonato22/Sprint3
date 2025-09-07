#!/bin/bash

Criar grupo de recursos,
az group create --location eastus --name rg-vm-challenge

Criar a VM na Azure,
az vm create \
  --resource-group rg-vm-challenge \
  --name vm-challenge \
  --image Canonical:ubuntu-24_04-lts:minimal:24.04.202505020 \
  --size Standard_B2s \
  --admin-username admin_fiap \
  --admin-password admin_fiap@123

Abrir a porta 8080 na VM,
az network nsg rule create \
  --resource-group rg-vm-challenge \
  --nsg-name vm-challengeNSG \
  --name port_8080 \
  --protocol tcp \
  --priority 1010 \
  --destination-port-range 8080 \
  --access Allow \
  --direction Inbound



Instalar o Docker,
sudo apt install -y docker.io


