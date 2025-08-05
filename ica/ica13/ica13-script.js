const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");

const width = (canvas.width = window.innerWidth);
const height = (canvas.height = window.innerHeight);
function random(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}
function randomRGB() {
  return `rgb(${random(0, 255)},${random(0, 255)},${random(0, 255)})`;
}

class Ball {
  constructor(x, y, velX, velY, color, size) {
    this.x = x;
    this.y = y;
    this.velX = velX;
    this.velY = velY;
    this.color = color;
    this.size = size;
  }

  draw() {
    ctx.beginPath();
    ctx.fillStyle = this.color;
    ctx.arc(this.x, this.y, this.size, 0, 2 * Math.PI);
    ctx.fill();
  }

  update() {
    if (this.x + this.size >= width) {
      this.velX = -Math.abs(this.velX);
    }

    if (this.x - this.size <= 0) {
      this.velX = Math.abs(this.velX);
    }

    if (this.y + this.size >= height) {
      this.velY = -Math.abs(this.velY);
    }

    if (this.y - this.size <= 0) {
      this.velY = Math.abs(this.velY);
    }

    this.x += this.velX;
    this.y += this.velY;
  }

  collisionDetect() {
    for (const ball of balls) {
      if (this === ball) break;//so collision code doesn't run twice
      const dx = this.x - ball.x;
      const dy = this.y - ball.y;
      const distance = Math.sqrt(dx * dx + dy * dy);

      if (distance < this.size + ball.size) {//if colliding
        ball.color = this.color = randomRGB();
        //using formulae from here: https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional_collision_with_two_moving_objects
        const v1=Math.sqrt(this.velX*this.velX+this.velY*this.velY);//magnitude of current velocity
        const v2=Math.sqrt(ball.velX*ball.velX+ball.velY*ball.velY);
        const theta1=Math.atan2(this.velY,this.velX);//global angle
        const theta2=Math.atan2(ball.velY,ball.velX);
        const m1=Math.PI*this.size*this.size;//mass (just using volume)
        const m2=Math.PI*ball.size*ball.size;
        const phi=Math.atan2(dy,dx);//contact angle
        this.velX=(v1*Math.cos(theta1-phi)*(m1-m2)+2*m2*v2*Math.cos(theta2-phi))/(m1+m2)*Math.cos(phi)+v1*Math.sin(theta1-phi)*Math.cos(phi+Math.PI/2);
        this.velY=(v1*Math.cos(theta1-phi)*(m1-m2)+2*m2*v2*Math.cos(theta2-phi))/(m1+m2)*Math.sin(phi)+v1*Math.sin(theta1-phi)*Math.sin(phi+Math.PI/2);
        ball.velX=(v2*Math.cos(theta2-phi)*(m2-m1)+2*m1*v1*Math.cos(theta1-phi))/(m1+m2)*Math.cos(phi)+v2*Math.sin(theta2-phi)*Math.cos(phi+Math.PI/2);
        ball.velY=(v2*Math.cos(theta2-phi)*(m2-m1)+2*m1*v1*Math.cos(theta1-phi))/(m1+m2)*Math.sin(phi)+v2*Math.sin(theta2-phi)*Math.sin(phi+Math.PI/2);
      }
    }
    //there's a small chance two balls can get stuck together an eventually fuse, just like in real life
  }
}

const balls = [];

while (balls.length < 25) {
  const size = random(10, 20);
  const ball = new Ball(
    random(0 + size, width - size),
    random(0 + size, height - size),
    random(-7, 7),
    random(-7, 7),
    randomRGB(),
    size
  );

  balls.push(ball);
}

function loop() {
  ctx.fillStyle = "rgba(0, 0, 0, 0.25)";
  ctx.fillRect(0, 0, width, height);

  for (const ball of balls) {
    ball.draw();
    ball.update();
    ball.collisionDetect();
  }

  requestAnimationFrame(loop);
}

loop();