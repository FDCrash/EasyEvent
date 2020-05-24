<template>
  <footer class="footer">
    <div class="container-fluid">
      <nav>
        <ul class="footer-menu">
          <li>
            <router-link :to="{path:'/overview'}">
              Dashboard
            </router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright text-center">
        &copy; Coded with
        <i class="fa fa-heart heart" /> by
        <a
          href="https://binarcode.com"
          target="_blank"
        >BinarCode</a>.
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
