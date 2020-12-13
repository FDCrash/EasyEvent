<template>
  <footer class="footer">
    <div class="container-fluid">
      <nav>
        <ul class="footer-menu">
          <li>
            <router-link :to="{path:'/home'}">
              Главная
            </router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright text-center">
        &copy;
        Designed by <a
          href="https://github.com/FDCrash"
          target="_blank"
        >FD_Crash</a>.
      </div>
    </div>
  </footer>
</template>
<script>
export default {}

</script>
<style>

</style>
