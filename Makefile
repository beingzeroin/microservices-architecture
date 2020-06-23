beingzero-build:
	docker-compose -f docker-compose.beingzero.yaml build

beingzero-up:
	docker-compose -f docker-compose.beingzero.yaml up -d --remove-orphans

beingzero-down:
	docker-compose -f docker-compose.beingzero.yaml down

beingzero-logs:
	docker-compose -f docker-compose.beingzero.yaml logs --follow

beingzero-status:
	docker-compose -f docker-compose.beingzero.yaml ps

